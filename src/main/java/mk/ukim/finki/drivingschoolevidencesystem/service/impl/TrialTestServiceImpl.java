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

import java.util.List;
import java.util.stream.Collectors;

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
        trialTest.setDrivingCourse(findDrivingCourseById(drivingCourseId));
        trialTest = trialTestRepository.save(trialTest);
        trialTestDTO = modelMaper.map(trialTest, TrialTestDTO.class);
        return trialTestDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public DrivingCourse findDrivingCourseById(long drivingCourseId) {
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

    @Override
    public List<TrialTestDTO> getAllTrialTestsForDrivingCourse(long drivingCourseId) {
        return trialTestRepository.findAllByDrivingCourse_Id(drivingCourseId)
                                    .stream()
                                    .map(trialTest -> modelMaper.map(trialTest, TrialTestDTO.class))
                                    .collect(Collectors.toList());
    }
}
