package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DriverLicenceDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.DriverLicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driverLicence")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class DriverLicenceController {
    @Autowired
    private DriverLicenceService driverLicenceService;

    @GetMapping("/all/{userId}")
    public List<DriverLicenceDTO> getAllForUser(@PathVariable long userId) {
        return driverLicenceService.getAllForUser(userId);
    }

    @PostMapping("/new/{candidateId}")
    public DriverLicenceDTO createNew(@RequestBody DriverLicenceDTO driverLicenceDTO, @PathVariable
            long candidateId) {
        return this.driverLicenceService.createNew(driverLicenceDTO, candidateId);
    }

    @PostMapping("/edit")
    public DriverLicenceDTO edit(@RequestBody DriverLicenceDTO driverLicenceDTO) {
        return this.driverLicenceService.edit(driverLicenceDTO);
    }

    @DeleteMapping("/remove/{driverLicenceId}")
    public long remove(@PathVariable long driverLicenceId) {
        this.driverLicenceService.remove(driverLicenceId);
        return driverLicenceId;
    }
}
