package mk.ukim.finki.trafficschoolevidencesystem.repository;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.DriverLicence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicenceRepository extends CrudRepository<DriverLicence, Long>{
    DriverLicence findByCategoryNameAndOwner_Id(String categoryName, long id);
}
