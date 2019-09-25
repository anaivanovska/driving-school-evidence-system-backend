package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Vehicle;
import mk.ukim.finki.trafficschoolevidencesystem.service.impl.VehicleServiceImpl;
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
