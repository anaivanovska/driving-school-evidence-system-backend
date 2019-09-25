package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.DriverLicenceDTO;
import mk.ukim.finki.trafficschoolevidencesystem.service.impl.DriverLicenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driverLicence")
public class DriverLicenceController {
    @Autowired
    private DriverLicenceServiceImpl driverLicenceService;

    @PostMapping("/createNew")
    public DriverLicenceDTO createNew(@RequestBody DriverLicenceDTO driverLicenceDTO, @RequestParam long candidateId) {
        return this.driverLicenceService.createNew(driverLicenceDTO, candidateId);
    }

    @PostMapping("/edit")
    public DriverLicenceDTO edit(@RequestBody DriverLicenceDTO driverLicenceDTO) {
        return this.driverLicenceService.edit(driverLicenceDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        this.driverLicenceService.remove(id);
        return id;
    }
}
