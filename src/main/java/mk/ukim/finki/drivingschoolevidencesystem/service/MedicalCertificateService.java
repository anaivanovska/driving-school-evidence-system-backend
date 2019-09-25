package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.MedicalCertificateDTO;

public interface MedicalCertificateService {
    MedicalCertificateDTO createNew(MedicalCertificateDTO medicalCertificateDTO, long candidateId);
    MedicalCertificateDTO edit(MedicalCertificateDTO medicalCertificateDTO);
    void remove(long id);
}
