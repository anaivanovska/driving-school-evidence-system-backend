package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface InstructorCategoryService {
    CategoryDTO addNewCategory(long userId, String type, String categoryName);
    List<CategoryDTO> addAllCategories(long userId, String type, List<String> names);
    List<CategoryDTO> getAllCategories(long userId, String type);
    Map<String, List<UserDTO>> getAllInstructorsGroupedByCategory(String type);
    List<UserDTO> getAllInstructorsOfTypeAndCategory(String type, String categoryName);
    Map<String, List<String>> getAllCategoriesGroupedByTypeForInstructor(long instructorId);
}
