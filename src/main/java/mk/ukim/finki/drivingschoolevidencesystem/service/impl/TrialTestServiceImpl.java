package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.TrialTestDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.TrialTest;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.TrialTestRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.TrialTestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrialTestServiceImpl implements TrialTestService {
    @Autowired
    private TrialTestRepository trialTestRepository;
    @Autowired
    private DrivingCourseRepository drivingCourseRepository;
    @Autowired
    private ModelMapper modelMaper;

    @Transactional
    @Override
    public TrialTestDTO createNew(TrialTestDTO trialTestDTO, long drivingCourseId){
        TrialTest trialTest = modelMaper.map(trialTestDTO, TrialTest.class);
        trialTest.setDrivingCourse(getDrivingCourse(drivingCourseId));
        trialTest = trialTestRepository.save(trialTest);
        trialTestDTO = modelMaper.map(trialTest, TrialTestDTO.class);
        return trialTestDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public DrivingCourse getDrivingCourse(long drivingCourseId) {
        DrivingCourse drivingCourse = drivingCourseRepository.findById(drivingCourseId)
                                .orElseThrow(() -> new TrafficSchoolException("Driving course with id = " + drivingCourseId + " does not exist"));
        return drivingCourse;
    }
    @Override
    public TrialTestDTO edit(TrialTestDTO trialTestDTO) {
        long id = trialTestDTO.getId();
        TrialTest trialTest = trialTestRepository.findById(id)
                                                .orElseThrow(() -> new TrafficSchoolException("Trial test with id: " + id + " does not exist"));
        trialTest.setTestNumber(trialTestDTO.getTestNumber());
        trialTest.setExaminationDate(trialTestDTO.getExaminationDate());
        trialTest.setPoints(trialTestDTO.getPoints());
        trialTest = trialTestRepository.save(trialTest);
        trialTestDTO = modelMaper.map(trialTest, TrialTestDTO.class);
        return trialTestDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        trialTestRepository.deleteById(id);
    }
}
