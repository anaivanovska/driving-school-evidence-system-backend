package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    public long removeCategory(@RequestParam long id) {
        this.categoryService.remove(id);
        return id;
    }

    @GetMapping("/all")
    public Page<CategoryDTO> getAllCategories(@PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        return this.categoryService.getAll(pageable);
    }


    @GetMapping("/allAsList")
    public List<CategoryDTO> getAllCategories() {
        return this.categoryService.getAll();
    }

    @GetMapping("/all/{candidateId}")
    public Page<CategoryDTO> getAllCategoriesFprCandidate(@PathVariable long candidateId, @PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        return this.categoryService.getAllForCandidate(candidateId, pageable);
    }
}
