package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);
    void deleteCategoryByName(String name);
}
