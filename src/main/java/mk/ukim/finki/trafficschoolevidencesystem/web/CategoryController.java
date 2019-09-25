package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.trafficschoolevidencesystem.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/createNew")
    public CategoryDTO createNewCategory(@RequestBody CategoryDTO categoryDTO) {
        return this.categoryService.createNew(categoryDTO);
    }

    @PostMapping("/edit")
    public CategoryDTO editCategory(@RequestBody CategoryDTO categoryDTO) {
        return this.categoryService.edit(categoryDTO);
    }

    @DeleteMapping("/remove")
    public String removeCategory(@RequestParam String name) {
        this.categoryService.remove(name);
        return name;
    }
}
