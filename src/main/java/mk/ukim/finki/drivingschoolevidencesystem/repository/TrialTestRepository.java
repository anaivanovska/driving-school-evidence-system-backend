package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.TrialTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialTestRepository extends CrudRepository<TrialTest, Long> {
    List<TrialTest> findAllByDrivingCourse_Id(long id);
}
