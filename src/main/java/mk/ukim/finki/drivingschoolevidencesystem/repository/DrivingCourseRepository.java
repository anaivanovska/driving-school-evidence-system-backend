package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrivingCourseRepository extends CrudRepository<DrivingCourse, Long> {
    DrivingCourse findByOrdinalNumber(String ordinalNumber);

    @Query("select distinct candidate from DrivingCourse")
    List<User> findAllCandidates();

}
