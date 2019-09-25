package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.DriverLicenceDTO;

public interface DriverLicenceService {
    DriverLicenceDTO createNew(DriverLicenceDTO driverLicenceDTO, long candidateId);
    DriverLicenceDTO edit(DriverLicenceDTO driverLicenceDTO);
    void remove(long id);
}
