package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.MedicalCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalCertificate")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class MedicalCertificateController {

    @Autowired
    private MedicalCertificateService medicalCertificateService;

    @GetMapping("/{drivingCourseId}")
    public MedicalCertificateDTO getMedicalCertificateForUser(@PathVariable long drivingCourseId) {
        return medicalCertificateService.getMedicalCertificateForDrivingCourse(drivingCourseId);
    }

    @PostMapping("/new/{drivingCourseId}")
    public MedicalCertificateDTO createNew(@RequestBody MedicalCertificateDTO medicalCertificateDTO, @PathVariable long drivingCourseId) {
        return this.medicalCertificateService.createNew(medicalCertificateDTO, drivingCourseId);
    }

    @PostMapping("/edit")
    public MedicalCertificateDTO edit(@RequestBody MedicalCertificateDTO medicalCertificateDTO) {
        return this.medicalCertificateService.edit(medicalCertificateDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        this.medicalCertificateService.remove(id);
        return id;
    }
}
