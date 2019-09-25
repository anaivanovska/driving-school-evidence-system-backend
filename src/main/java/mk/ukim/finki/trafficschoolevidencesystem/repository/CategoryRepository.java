package mk.ukim.finki.trafficschoolevidencesystem.repository;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findCategoryByName(String name);
    void deleteCategoryByName(String name);
}
