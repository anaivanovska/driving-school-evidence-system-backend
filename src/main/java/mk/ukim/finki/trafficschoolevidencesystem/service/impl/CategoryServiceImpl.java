package mk.ukim.finki.trafficschoolevidencesystem.service.impl;

import mk.ukim.finki.trafficschoolevidencesystem.TrafficSchoolEvidenceSystemApplication;
import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.trafficschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.trafficschoolevidencesystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
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
        Category category = categoryRepository.findCategoryByName(categoryDTO.getName());
        if(category == null) {
            throw new TrafficSchoolException("Category with name " + categoryDTO.getName() + " does not exist.");
        }
        Category newCategory = modelMapper.map(categoryDTO, Category.class);
        newCategory.setId(category.getId());
        newCategory = categoryRepository.save(newCategory);
        categoryDTO = modelMapper.map(newCategory, CategoryDTO.class);
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


}
