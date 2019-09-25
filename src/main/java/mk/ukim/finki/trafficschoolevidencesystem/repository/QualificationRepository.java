package mk.ukim.finki.trafficschoolevidencesystem.repository;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {
    Qualification findByTypeAndCandidate_Id(String type, long id);
    Qualification findByIdNotAndTypeAndCandidate_Id(long id, String type, long candidateId);
}
