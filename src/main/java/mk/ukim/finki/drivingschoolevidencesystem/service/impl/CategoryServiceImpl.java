package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public CategoryDTO createNew(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findByName(categoryDTO.getName());
        if(category != null) {
            throw new TrafficSchoolException("Category with name: " + categoryDTO.getName() +" exists.");
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
    public Collection<CategoryDTO> findAll() {
        Iterable<Category> categoryIterable = categoryRepository.findAll();
        Collection<CategoryDTO> categories = new ArrayList<>();
        categoryIterable.forEach(category -> {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
            categories.add(categoryDTO);
        });
        return categories;
    }
}
