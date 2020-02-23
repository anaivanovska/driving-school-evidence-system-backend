package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseInputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DrivingCourseService {
    DrivingCourseOutputDTO createNew(String ordinalNuber, long vehicleId, long lecturerId, long candidateId);

    DrivingCourseOutputDTO edit(long id, String ordinalNumber, long vehicleId, long lecturerId);

    void remove(long id);

    Page<DrivingCourseOutputDTO> getAllForUser(long userId, Pageable pageable);

    Page<DrivingCourseOutputDTO> getAll(Pageable pageable);

}
