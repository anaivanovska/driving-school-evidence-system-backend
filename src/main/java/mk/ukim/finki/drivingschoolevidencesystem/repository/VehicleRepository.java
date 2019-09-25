package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findByRegistrationNumber(String registrationNumber);
    Vehicle findByTypeAndBrandAndRegistrationNumber(String type, String brand, String registrationNumber);
}
