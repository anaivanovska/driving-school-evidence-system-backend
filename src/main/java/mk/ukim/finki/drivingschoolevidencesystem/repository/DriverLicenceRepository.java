package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DriverLicence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicenceRepository extends CrudRepository<DriverLicence, Long>{
    DriverLicence findByCategoryNameAndOwner_Id(String categoryName, long id);
}
