package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.QualificationDTO;

public interface QualificationService {
    QualificationDTO createNew(QualificationDTO qualificationDTO, long drivingCourseId);
    QualificationDTO edit(QualificationDTO qualificationDTO);
    void remove(long id);
}
