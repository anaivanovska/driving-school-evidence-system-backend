package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {
    Qualification findByTypeAndDrivingCourse_Id(String type, long drivingCourseId);
    Qualification findByIdNotAndTypeAndDrivingCourse_Id(long id, String type, long drivingCourseId);
}
