package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;

import java.util.Collection;

public interface CategoryService {
    CategoryDTO createNew(CategoryDTO categoryDTO);
    CategoryDTO edit(CategoryDTO categoryDTO);
    long remove(long id);
    Collection<CategoryDTO> findAll();
}
