package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.TrialTestDTO;

public interface TrialTestService {
    TrialTestDTO createNew(TrialTestDTO trialTestDTO, long candidateId);
    TrialTestDTO edit(TrialTestDTO trialTestDTO);
    void remove(long id);
}
