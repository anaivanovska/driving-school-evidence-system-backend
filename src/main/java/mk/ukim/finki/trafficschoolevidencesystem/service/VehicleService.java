package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.VehicleDTO;

public interface VehicleService {
    VehicleDTO createNew(VehicleDTO vehicleDTO);
    VehicleDTO edit(VehicleDTO vehicleDTO);
    void remove(long id);
}
