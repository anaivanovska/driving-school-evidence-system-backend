package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingCourseRepository extends CrudRepository<DrivingCourse, Long> {
    DrivingCourse findByOrdinalNumber(String ordinalNumber);
}
