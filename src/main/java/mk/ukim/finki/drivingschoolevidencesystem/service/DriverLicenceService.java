package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DriverLicenceDTO;

public interface DriverLicenceService {
    DriverLicenceDTO createNew(DriverLicenceDTO driverLicenceDTO, long candidateId);
    DriverLicenceDTO edit(DriverLicenceDTO driverLicenceDTO);
    void remove(long id);
}
