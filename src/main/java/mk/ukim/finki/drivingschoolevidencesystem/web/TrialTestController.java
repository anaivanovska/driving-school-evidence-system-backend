package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.TrialTestDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.TrialTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trialTest")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class TrialTestController {

    @Autowired
    protected TrialTestService trialTestService;

    @GetMapping("/all/{drivingCourseId}")
    public List<TrialTestDTO> getAllForDrivingCourse(@PathVariable long drivingCourseId) {
        return trialTestService.getAllTrialTestsForDrivingCourse(drivingCourseId);
    }

    @PostMapping("/new/{drivingCourseId}")
    public TrialTestDTO createNew(@RequestBody TrialTestDTO trialTestDTO, @PathVariable long drivingCourseId) {
        return this.trialTestService.createNew(trialTestDTO, drivingCourseId);
    }

    @PostMapping("/edit")
    public TrialTestDTO edit(@RequestBody TrialTestDTO trialTestDTO) {
        return this.trialTestService.edit(trialTestDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id){
        this.trialTestService.remove(id);
        return id;
    }
}
