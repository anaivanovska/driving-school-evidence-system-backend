package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO createNew(VehicleDTO vehicleDTO);
    VehicleDTO edit(VehicleDTO vehicleDTO);
    void remove(long id);
    List<VehicleDTO> findAllVehicles();
}
