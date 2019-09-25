package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.QualificationDTO;

public interface QualificationService {
    QualificationDTO createNew(QualificationDTO qualificationDTO, long candidateId);
    QualificationDTO edit(QualificationDTO qualificationDTO, long candidateId);
    void remove(long id);
}
