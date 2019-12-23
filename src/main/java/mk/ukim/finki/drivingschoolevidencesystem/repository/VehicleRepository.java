package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findByRegistrationNumber(String registrationNumber);
    List<Vehicle> findAll();
    Vehicle findByTypeAndBrandAndRegistrationNumber(String type, String brand, String registrationNumber);
}
