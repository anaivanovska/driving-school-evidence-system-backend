package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    CategoryDTO createNew(CategoryDTO categoryDTO);
    CategoryDTO edit(CategoryDTO categoryDTO);
    long remove(long id);
    Page<CategoryDTO> getAll(Pageable pageable);
    List<CategoryDTO> getAll();
    Page<CategoryDTO> getAllForCandidate(long candidateId, Pageable pageable);
    Page<CategoryDTO> search(String value, Pageable pageable);
}
