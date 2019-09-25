package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.QualificationDTO;
import mk.ukim.finki.trafficschoolevidencesystem.service.QualificationService;
import mk.ukim.finki.trafficschoolevidencesystem.service.impl.QualificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {
    @Autowired
    private QualificationServiceImpl qualificationService;

    @PostMapping("/createNew")
    public QualificationDTO createNew(@RequestBody QualificationDTO qualificationDTO, @RequestParam long candidateId) {
        return qualificationService.createNew(qualificationDTO, candidateId);
    }

    @PostMapping("/edit")
    public QualificationDTO edit(@RequestBody QualificationDTO qualificationDTO, @RequestParam long candidateId) {
        return qualificationService.edit(qualificationDTO, candidateId);
    }

    @DeleteMapping
    public long remove(@RequestParam long id) {
        this.qualificationService.remove(id);
        return id;
    }


}
