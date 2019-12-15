package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userCategory")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class UserCategoryController {
    @Autowired
    private UserCategoryService userCategoryService;

    @PostMapping("/{id}/{role}/category")
    public CategoryDTO addNew(@PathVariable long id,@PathVariable String role, @RequestParam String name) {
        return userCategoryService.addNewCategory(id, role, name);
    }

    @PostMapping("/{id}/{role}/categories")
    public List<CategoryDTO> addCategories(@PathVariable long id, @PathVariable String role, @RequestBody List<String> names) {
        return userCategoryService.addAllCategories(id, role, names);
    }

    @GetMapping("/{id}/{role}/categories")
    public List<CategoryDTO> getAllCategoriesForInstructor(@PathVariable long id, @PathVariable String role) {
        return userCategoryService.getAllCategories(id, role);
    }
}
