package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {
    Qualification findByTypeAndDrivingCourse_Id(String type, long drivingCourseId);
    List<Qualification> findByDrivingCourse_Id(long id);
}
