package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.InstructorDTO;

import java.util.Collection;

public interface InstructorService {
    InstructorDTO createNew(InstructorDTO instructorDTO);
    InstructorDTO edit(InstructorDTO instructorDTO);
    void remove(long id);
    Collection<InstructorDTO> getAll();
}
