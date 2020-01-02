package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructorCategory")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class InstructorCategoryController {
    @Autowired
    private InstructorCategoryService instructorCategoryService;

    @PostMapping("/{id}/{type}/category")
    public CategoryDTO addNew(@PathVariable long id,@PathVariable String type, @RequestParam String name) {
        return instructorCategoryService.addNewCategory(id, type, name);
    }

    @PostMapping("/{id}/{type}/categories")
    public List<CategoryDTO> addCategories(@PathVariable long id, @PathVariable String type, @RequestBody List<String> names) {
        return instructorCategoryService.addAllCategories(id, type, names);
    }

    @GetMapping("/{id}/{type}/categories")
    public List<CategoryDTO> getAllCategoriesForInstructor(@PathVariable long id, @PathVariable String type) {
        return instructorCategoryService.getAllCategories(id, type);
    }

    @GetMapping("/{type}/instructorsAndCategories")
    public Map<String, List<UserDTO>> getAllUsersGroupedByategory(@PathVariable String type) {
        return instructorCategoryService.getAllInstructorsGroupedByCategory(type);
    }

    @GetMapping("/{type}/{categoryName}/all/instructors")
    public List<UserDTO> getAllInstructorsOfType(@PathVariable String type, @PathVariable String categoryName) {
        return instructorCategoryService.getAllInstructorsOfTypeAndCategory(type, categoryName);
    }

    @GetMapping("/allCategories/{instructorId}")
    public Map<String, List<String>> getAllCategoriesForInstructor(@PathVariable long instructorId) {
        return instructorCategoryService.getAllCategoriesGroupedByTypeForInstructor(instructorId);
    }

}
