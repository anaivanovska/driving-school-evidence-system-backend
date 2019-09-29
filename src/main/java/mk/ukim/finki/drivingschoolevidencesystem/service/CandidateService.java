package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CandidateDTO;import org.springframework.data.domain.Page;

public interface CandidateService {
    CandidateDTO createNew(CandidateDTO candidateDTO);
    CandidateDTO edit(CandidateDTO candidateDTO);
    void remove(long id);
    Page<CandidateDTO> getAll(int pageNumber);
}
