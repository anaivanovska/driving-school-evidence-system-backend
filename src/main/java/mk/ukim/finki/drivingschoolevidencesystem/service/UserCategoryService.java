package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;

import java.util.List;
import java.util.Map;

public interface UserCategoryService {
    CategoryDTO addNewCategory(long userId, String role, String categoryName);
    List<CategoryDTO> addAllCategories(long userId, String role, List<String> names);
    List<CategoryDTO> getAllCategories(long userId, String role);
    Map<String, List<UserDTO>> getAllUsersGroupedByCategory(String role);
}
