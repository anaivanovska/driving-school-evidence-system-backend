package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Category;

public interface CategoryService {
    CategoryDTO createNew(CategoryDTO categoryDTO);
    CategoryDTO edit(CategoryDTO categoryDTO);
    void remove(String name);
}
