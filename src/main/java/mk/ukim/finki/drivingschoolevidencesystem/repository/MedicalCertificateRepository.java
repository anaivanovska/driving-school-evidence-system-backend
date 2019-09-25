package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.MedicalCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCertificateRepository extends CrudRepository<MedicalCertificate, Long> {
    MedicalCertificate findByNumberAndCandidate_Id(String number, long id);
}
