package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface VehicleService {
    VehicleDTO createNew(VehicleDTO vehicleDTO);
    VehicleDTO edit(VehicleDTO vehicleDTO);
    void remove(long id);
    Page<VehicleDTO> findAllVehicles(Pageable pageable);
    Page<VehicleDTO> search(String value, Pageable pageable);
    List<VehicleDTO> findAllVehicles();
}
