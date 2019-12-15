package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;

import java.util.List;

public interface UserCategoryService {
    CategoryDTO addNewCategory(long userId, String role, String categoryName);
    List<CategoryDTO> addAllCategories(long userId, String role, List<String> names);
    List<CategoryDTO> getAllCategories(long userId, String role);
}
