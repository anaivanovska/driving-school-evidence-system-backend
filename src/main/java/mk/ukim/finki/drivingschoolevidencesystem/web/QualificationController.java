package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.QualificationDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qualification")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class QualificationController {
    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/all/{drivingCourseId}")
    public List<QualificationDTO> getAllQualificationsForDrivingCourse(@PathVariable long drivingCourseId) {
        return qualificationService.getAllQualificationsForDrivingCourse(drivingCourseId);
    }

    @PostMapping("/new/{drivingCourseId}")
    public QualificationDTO createNew(@RequestBody QualificationDTO qualificationDTO, @PathVariable long drivingCourseId) {
        return qualificationService.createNew(qualificationDTO, drivingCourseId);
    }

    @PostMapping("/edit")
    public QualificationDTO edit(@RequestBody QualificationDTO qualificationDTO) {
        return qualificationService.edit(qualificationDTO);
    }

    @DeleteMapping
    public long remove(@RequestParam long id) {
        this.qualificationService.remove(id);
        return id;
    }


}
