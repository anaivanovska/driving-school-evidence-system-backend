package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.MedicalCertificate;

public interface MedicalCertificateService {
    MedicalCertificateDTO createNew(MedicalCertificateDTO medicalCertificateDTO, long candidateId);
    MedicalCertificateDTO edit(MedicalCertificateDTO medicalCertificateDTO);
    void remove(long id);
}
