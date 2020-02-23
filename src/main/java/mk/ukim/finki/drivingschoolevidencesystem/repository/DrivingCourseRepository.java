package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingCourseRepository extends CrudRepository<DrivingCourse, Long> {
    DrivingCourse findByOrdinalNumber(String ordinalNumber);
    Page<DrivingCourse> findAllByCandidate_Id(long id, Pageable pageable);
    Page<DrivingCourse> findAll(Pageable pageable);
}
