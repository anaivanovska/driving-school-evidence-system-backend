package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface InstructorCategoryService {
    CategoryDTO addNewCategory(long userId, String type, String categoryName);
    List<CategoryDTO> addAllCategories(long userId, String type, List<String> names);
    List<CategoryDTO> getAllCategoriesByType(long userId, String type);
    Page<CategoryDTO> getAllCategories(long userId, Pageable pageable);
    Map<String, List<UserDTO>> getAllInstructorsGroupedByCategory(String type);
    List<UserDTO> getAllInstructorsOfTypeAndCategory(String type, String categoryName);
    Map<String, List<String>> getAllCategoriesGroupedByTypeForInstructor(long instructorId);
}
