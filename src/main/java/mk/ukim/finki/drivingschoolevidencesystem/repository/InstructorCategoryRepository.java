package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.InstructorCategory;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorCategoryRepository extends JpaRepository<InstructorCategory, Long> {
    InstructorCategory findByInstructor_IdAndCategory_Name(long instructorId, String categoryName);
    List<InstructorCategory> findAllByInstructor_Id(long id);
}
