package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.InstructorCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorCategoryRepository extends JpaRepository<InstructorCategory, Long> {
    List<InstructorCategory> findAllByUser_Id(long userId);
    List<InstructorCategory> findAllByUser_IdAndType(long id, String type);
    List<InstructorCategory> findAllByType(String type);
    Page<InstructorCategory> findAllByType(String type, Pageable pageable);
    List<InstructorCategory> findAllByTypeAndCategory_Name(String type, String categoryName);
    void deleteAllByUser_Id(long id);

}