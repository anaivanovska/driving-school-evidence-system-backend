package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO createNew(CategoryDTO categoryDTO);
    CategoryDTO edit(CategoryDTO categoryDTO);
    void remove(String name);
}
