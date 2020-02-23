package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.search.SearchRepositoryImpl;
import mk.ukim.finki.drivingschoolevidencesystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DrivingCourseRepository drivingCourseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SearchRepositoryImpl searchRepositoryImpl;

    @Transactional
    @Override
    public CategoryDTO createNew(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByName(categoryDTO.getName());
        if (category != null) {
            throw new TrafficSchoolException("Category with name: " + categoryDTO.getName() + " exists.");
        }
        category = modelMapper.map(categoryDTO, Category.class);
        category = categoryRepository.save(category);
        categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Transactional
    @Override
    public CategoryDTO edit(CategoryDTO categoryDTO) {
        long id = categoryDTO.getId();
        String name = categoryDTO.getName();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Category with id  " + id + " name " + name + " does not exist."));

        category.setName(name);
        category.setPrice(categoryDTO.getPrice());
        category = categoryRepository.save(category);
        categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Transactional
    @Override
    public long remove(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Category with id " + id + " does not exist."));
        categoryRepository.delete(category);
        return id;
    }

    @Transactional
    @Override
    public Page<CategoryDTO> getAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> modelMapper.map(category, CategoryDTO.class));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(category -> modelMapper.map(category, CategoryDTO.class))
                                 .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Page<CategoryDTO> getAllForCandidate(long candidateId, Pageable pageable) {
        return drivingCourseRepository.findAllByCandidate_Id(candidateId, pageable)
                                        .map(drivingCourse ->  modelMapper.map(drivingCourse.getVehicle().getCategory(), CategoryDTO.class));
    }

    @Transactional
    @Override
    public Page<CategoryDTO> search(String value, Pageable pageable) {
        List<CategoryDTO> categories =  searchRepositoryImpl.searchPhrase(Category.class, value, "name", "price")
                                                                        .stream()
                                                                        .map(category -> modelMapper.map(category, CategoryDTO.class))
                                                                        .collect(Collectors.toList());
        Page<CategoryDTO> categoryDTOPage = new PageImpl<>(categories, pageable, categories.size());
        return categoryDTOPage;

    }
}
