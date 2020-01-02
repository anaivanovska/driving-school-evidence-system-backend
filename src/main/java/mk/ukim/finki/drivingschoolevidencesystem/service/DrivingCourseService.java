package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseInputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseOutputDTO;

public interface DrivingCourseService {
    long createNew(DrivingCourseInputDTO drivingCourseInputDTO, long candidateId);
    DrivingCourseOutputDTO edit(DrivingCourseInputDTO drivingCourseInputDTO);
    void remove(long id);
}
