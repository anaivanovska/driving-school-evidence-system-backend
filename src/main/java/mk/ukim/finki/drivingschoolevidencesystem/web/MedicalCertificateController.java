package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.MedicalCertificateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalCertificate")
public class MedicalCertificateController {
    @Autowired
    private MedicalCertificateServiceImpl medicalCertificateService;

    @PostMapping("/createNew")
    public MedicalCertificateDTO createNew(@RequestBody MedicalCertificateDTO medicalCertificateDTO, @RequestParam long candidateId) {
        return this.medicalCertificateService.createNew(medicalCertificateDTO, candidateId);
    }

    @PostMapping("/edit")
    public MedicalCertificateDTO createNew(@RequestBody MedicalCertificateDTO medicalCertificateDTO) {
        return this.medicalCertificateService.edit(medicalCertificateDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        this.medicalCertificateService.remove(id);
        return id;
    }
}
