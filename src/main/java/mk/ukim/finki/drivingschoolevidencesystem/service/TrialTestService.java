package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.TrialTestDTO;

public interface TrialTestService {
    TrialTestDTO createNew(TrialTestDTO trialTestDTO, long candidateId);
    TrialTestDTO edit(TrialTestDTO trialTestDTO);
    void remove(long id);
}
