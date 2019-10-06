package mk.ukim.finki.drivingschoolevidencesystem.web;

import jdk.nashorn.internal.objects.annotations.Getter;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructorCategory")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class InstructorCategoryController {
    @Autowired
    private InstructorCategoryService instructorCategoryService;

    @PostMapping("/addNew")
    public CategoryDTO addNew(@RequestParam long id, @RequestParam String name) {
        return instructorCategoryService.addNewCategory(id, name);
    }

    @GetMapping("/allCategories/{instructorId}")
    public List<CategoryDTO> getAllCategoriesForInstructor(@PathVariable long instructorId) {
        return instructorCategoryService.getAllCategoriesForInstructor(instructorId);
    }
}
