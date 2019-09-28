package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CandidateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/createNew")
    public CandidateDTO createNew(@RequestBody CandidateDTO candidateDTO) {
        return this.candidateService.createNew(candidateDTO);
    }

    @PostMapping("/edit")
    public UserDTO edit(@RequestBody CandidateDTO candidateDTO) {
        return this.candidateService.edit(candidateDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        candidateService.remove(id);
        return id;
    }
}
