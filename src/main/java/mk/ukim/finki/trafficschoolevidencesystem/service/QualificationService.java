package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.QualificationDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Qualification;

public interface QualificationService {
    QualificationDTO createNew(QualificationDTO qualificationDTO, long candidateId);
    QualificationDTO edit(QualificationDTO qualificationDTO, long candidateId);
    void remove(long id);
}
