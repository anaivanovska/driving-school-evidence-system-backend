package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseInputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseOutputDTO;

public interface DrivingCourseService {
    DrivingCourseOutputDTO createNew(DrivingCourseInputDTO drivingCourseInputDTO, long candidateId);
    DrivingCourseOutputDTO edit(DrivingCourseInputDTO drivingCourseInputDTO, long candidateId);
    void remove(long id);
}
