package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.InstructorDTO;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface InstructorService {
    InstructorDTO createNew(InstructorDTO instructorDTO);
    InstructorDTO edit(InstructorDTO instructorDTO);
    void remove(long id);
    Page<InstructorDTO> getAll(int pageNumber);
}
