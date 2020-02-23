package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("instructorCategoryService")
public class InstructorCategoryServiceImpl implements InstructorCategoryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstructorCategoryRepository instructorCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CategoryDTO addNewCategory(long userId, String type, String categoryName) {
        InstructorCategory instructorCategory =  instructorCategory = new InstructorCategory();
        instructorCategory.setCategory(findCategoryByName(categoryName));
        instructorCategory.setUser(findUserById(userId));
        instructorCategory.setType(type);
        instructorCategory = instructorCategoryRepository.save(instructorCategory);
        CategoryDTO categoryDTO = modelMapper.map(instructorCategory.getCategory(), CategoryDTO.class);
        return categoryDTO;
    }

    @Transactional
    @Override
    public List<CategoryDTO> addAllCategories(long userId, String type, List<String> names) {
        List<CategoryDTO> categories = new ArrayList<>();
        instructorCategoryRepository.deleteAllByUser_Id(userId);
        names.forEach(name -> {
            CategoryDTO categoryDTO = this.addNewCategory(userId, type, name);
            categories.add(categoryDTO);
        });
        return categories;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User findUserById(long id) {
        User user =  userRepository.findById(id)
                            .orElseThrow(() -> new TrafficSchoolException("User with id " + id + " not found"));
        return user;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Category findCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null) {
           throw new TrafficSchoolException("Category with name " + name + " not found");
        }
        return category;
    }

    @Transactional
    @Override
    public List<CategoryDTO> getAllCategoriesByType(long userId, String type) {
        List<InstructorCategory> userCategories = instructorCategoryRepository.findAllByUser_IdAndType(userId, type);
        return userCategories.stream()
                        .map(userCategory -> {
                            CategoryDTO categoryDTO = modelMapper.map(userCategory.getCategory(), CategoryDTO.class);
                            return categoryDTO;
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    @Override
    public Page<CategoryDTO> getAllCategories(long userId, Pageable pageable) {
        return instructorCategoryRepository.findAllByUser_Id(userId, pageable)
                                            .map(instructorCategory -> modelMapper.map(instructorCategory.getCategory(), CategoryDTO.class));
    }

    @Transactional
    @Override
    public Map<String, List<UserDTO>> getAllInstructorsGroupedByCategory(String type) {
         List<InstructorCategory> userCategories = instructorCategoryRepository.findAllByType(type);

        Map<String, List<UserDTO>> result = new HashMap<>();
        for(InstructorCategory instructorCategory : userCategories) {
            String categoryName = instructorCategory.getCategory().getName();
            UserDTO user = modelMapper.map(instructorCategory.getUser(), UserDTO.class);
            List<UserDTO> users;
            if (result.containsKey(categoryName)) {
               users = result.get(categoryName);
                users.add(user);
            } else {
                users = new ArrayList<>();
                users.add(user);
                result.put(categoryName, users);
            }
        }

        return result;
    }

    @Transactional
    @Override
    public List<UserDTO> getAllInstructorsOfTypeAndCategory(String type, String categoryName) {
            return instructorCategoryRepository.findAllByTypeAndCategory_Name(type, categoryName)
                                                .stream()
                                                .map(instructorCategory -> modelMapper.map(instructorCategory.getUser(), UserDTO.class))
                                                .distinct()
                                                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Map<String, List<String>> getAllCategoriesGroupedByTypeForInstructor(long instructorId) {
        List<InstructorCategory> allCategories = instructorCategoryRepository.findAllByUser_Id(instructorId);
        Map<String, List<String>> categoriesForInstructorOfType  = new HashMap<>();

        if (allCategories.size() > 0) {
            List<String> categories = allCategories.stream()
                                                    .map(category -> category.getCategory().getName())
                                                    .collect(Collectors.toList());
            categoriesForInstructorOfType.put(allCategories.get(0).getType(), categories);
        }
        return categoriesForInstructorOfType;
    }

    @Transactional (propagation = Propagation.MANDATORY)
    public List<Category> findAllCategories(List<String> names) {
        return categoryRepository.findCategoriesByName(names);
    }

}