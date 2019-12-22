package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("instructorCategoryService")
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserCategoryRepository userCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CategoryDTO addNewCategory(long userId, String role, String categoryName) {
        UserCategory userCategory = userCategoryRepository.findByUser_IdAndCategory_NameAndRole(userId, categoryName, role);
        if(userCategory == null) {
            userCategory = new UserCategory();
            userCategory.setCategory(findCategoryByName(categoryName));
            userCategory.setUser(findUserById(userId));
            userCategory.setRole(role);
            userCategory = userCategoryRepository.save(userCategory);
            CategoryDTO categoryDTO = modelMapper.map(userCategory.getCategory(), CategoryDTO.class);
            return categoryDTO;

        }

        throw new TrafficSchoolException("Category with name  " + categoryName + " for instructor with id " + userCategory + " already exists");
    }

    @Transactional
    @Override
    public List<CategoryDTO> addAllCategories(long userId, String role, List<String> names) {
        List<CategoryDTO> categories = new ArrayList<>();
        names.forEach(name -> {
            CategoryDTO categoryDTO = this.addNewCategory(userId, role, name);
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
    public List<CategoryDTO> getAllCategories(long userId, String role) {
        List<UserCategory> userCategories = userCategoryRepository.findAllByUser_IdAndRole(userId, role);
        return userCategories.stream()
                        .map(userCategory -> {
                            CategoryDTO categoryDTO = modelMapper.map(userCategory.getCategory(), CategoryDTO.class);
                            return categoryDTO;
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Map<String, List<UserDTO>> getAllUsersGroupedByCategory(String role) {
        List<UserCategory> userCategories = userCategoryRepository.findAllByRole(role);

        Map<String, List<UserDTO>> result = new HashMap<>();
        for(UserCategory userCategory : userCategories) {
            String categoryName = userCategory.getCategory().getName();
            UserDTO user = modelMapper.map(userCategory.getUser(), UserDTO.class);
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

    @Transactional (propagation = Propagation.MANDATORY)
    public List<Category> findAllCategories(List<String> names) {
        return categoryRepository.findCategoriesByName(names);
    }

}