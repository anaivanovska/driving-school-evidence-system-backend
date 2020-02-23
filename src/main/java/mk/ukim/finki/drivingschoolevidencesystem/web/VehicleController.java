package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.VehicleDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class VehicleController {
    @Autowired
    private VehicleServiceImpl vehicleService;

    @PostMapping("/new")
    public VehicleDTO createNew(@RequestBody VehicleDTO vehicleDTO) {
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

    @GetMapping("/all/")
    public List<VehicleDTO> getAll() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/all")
    public Page<VehicleDTO> getAll(@PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        return vehicleService.findAllVehicles(pageable);
    }
}
