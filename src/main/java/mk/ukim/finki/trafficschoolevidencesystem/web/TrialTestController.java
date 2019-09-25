package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.TrialTestDTO;
import mk.ukim.finki.trafficschoolevidencesystem.service.impl.TrialTestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trialTest")
public class TrialTestController {
    @Autowired
    private TrialTestServiceImpl trialTestService;

    @PostMapping("/createNew")
    public TrialTestDTO createNew(@RequestBody TrialTestDTO trialTestDTO, @RequestParam long candidateId) {
        return this.trialTestService.createNew(trialTestDTO, candidateId);
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
