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
import java.util.Collections;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public CategoryDTO createNew(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findCategoryByName(categoryDTO.getName());
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
    public void remove(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        if(category == null) {
            throw new TrafficSchoolException("Category with name " + name + " does not exist.");
        }
        categoryRepository.deleteCategoryByName(name);
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
