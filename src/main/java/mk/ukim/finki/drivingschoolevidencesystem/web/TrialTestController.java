package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.TrialTestDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.TrialTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trialTest")public class TrialTestController {
    @Autowired
    private TrialTestServiceImpl trialTestService;

    @PostMapping("/createNew")
    public TrialTestDTO createNew(@RequestBody TrialTestDTO trialTestDTO, @RequestParam long drivingCourseId) {
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
