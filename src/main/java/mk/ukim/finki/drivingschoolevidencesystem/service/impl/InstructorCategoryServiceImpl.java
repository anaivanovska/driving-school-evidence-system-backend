package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.InstructorCategory;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.InstructorCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("instructorCategoryService")
public class InstructorCategoryServiceImpl implements InstructorCategoryService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstructorCategoryRepository instructorCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    @Override
    public CategoryDTO addNewCategory(long instructorId, String categoryName) {
        InstructorCategory instructorCategory = instructorCategoryRepository.findByInstructor_IdAndCategory_Name(instructorId, categoryName);
        if(instructorCategory == null) {
            instructorCategory = new InstructorCategory();
            instructorCategory.setCategory(findCategoryWihtId(categoryName));
            instructorCategory.setInstructor(findInstructorWihtId(instructorId));
            instructorCategory = instructorCategoryRepository.save(instructorCategory);
            CategoryDTO categoryDTO = modelMapper.map(instructorCategory.getCategory(), CategoryDTO.class);
            return categoryDTO;

        }

        throw new TrafficSchoolException("Category with name  " + categoryName + " for instructor with id " + instructorCategory + " already exists");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User findInstructorWihtId(long id) {
        User instructor = userRepository.findByIdAndRoles_name(id, Constants.Role.INSTRUCTOR.name())
                            .orElseThrow(() -> new TrafficSchoolException("Instructor with id " + id + " not found"));
        return instructor;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Category findCategoryWihtId(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null) {
           throw new TrafficSchoolException("Category with name " + name + " not found");
        }
        return category;
    }

    @Transactional
    @Override
    public List<CategoryDTO> getAllCategoriesForInstructor(long instructorId) {
        List<InstructorCategory> instructorCategories = instructorCategoryRepository.findAllByInstructor_Id(instructorId);
        return instructorCategories.stream()
                        .map(instructorCategory -> {
                            CategoryDTO categoryDTO = modelMapper.map(instructorCategory.getCategory(), CategoryDTO.class);
                            return categoryDTO;
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}
