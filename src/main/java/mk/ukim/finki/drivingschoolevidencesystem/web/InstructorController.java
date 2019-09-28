package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.InstructorDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.CandidateService;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/createNew")
    public InstructorDTO createNew(@RequestBody InstructorDTO instructorDTO) {
        return this.instructorService.createNew(instructorDTO);
    }

    @PostMapping("/edit")
    public UserDTO edit(@RequestBody InstructorDTO instructorDTO) {
        return this.instructorService.edit(instructorDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        instructorService.remove(id);
        return id;
    }

}
