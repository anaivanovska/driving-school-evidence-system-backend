package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping("/createNew")
    public VehicleDTO createNew(@RequestBody VehicleDTO vehicleDTO){
        return this.vehicleService.createNew(vehicleDTO);
    }

    @PostMapping("/edit")
    public VehicleDTO edit(@RequestBody VehicleDTO vehicleDTO) {
        return this.vehicleService.edit(vehicleDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        this.vehicleService.remove(id);
        return id;
    }
}