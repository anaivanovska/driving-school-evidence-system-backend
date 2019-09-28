package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CandidateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Candidate;

import java.util.Collection;

public interface CandidateService {
    CandidateDTO createNew(CandidateDTO candidateDTO);
    CandidateDTO edit(CandidateDTO candidateDTO);
    void remove(long id);
    Collection<CandidateDTO> getAll();
}
