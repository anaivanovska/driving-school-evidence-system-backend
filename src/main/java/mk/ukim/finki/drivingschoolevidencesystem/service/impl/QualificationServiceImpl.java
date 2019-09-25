package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.QualificationDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Qualification;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.QualificationRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.QualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualificationServiceImpl implements QualificationService{
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private DrivingCourseRepository drivingCourseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public QualificationDTO createNew(QualificationDTO qualificationDTO, long candidateId) {
        Qualification qualification = qualificationRepository.findByTypeAndCandidate_Id(qualificationDTO.getType(),
                                                                                        candidateId);
        if(qualification != null) {
            throw new TrafficSchoolException("Qualification of type = " + qualificationDTO.getType() + " for candidate with id = " + candidateId + " already exists");
        }

        qualification = modelMapper.map(qualificationDTO, Qualification.class);
        qualification.setDrivingCourse();
        qualification = qualificationRepository.save(qualification);
        qualificationDTO = modelMapper.map(qualification,QualificationDTO.class);
        return qualificationDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Candidate findCandidate(long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Candidate with id = " + id + " does not exist"));
        return candidate;
    }

    @Transactional
    @Override
    public QualificationDTO edit(QualificationDTO qualificationDTO, long candidateId) {
        long id = qualificationDTO.getId();
        Qualification qualification = this.qualificationRepository.findById(id)
                                                                .orElseThrow(() -> new TrafficSchoolException("Qualification with id = " + id + " does not exist"));

        Qualification tmpQualification = this.qualificationRepository.findByIdNotAndTypeAndCandidate_Id(id, qualificationDTO.getType(), candidateId);
        if(tmpQualification != null) {
            throw new TrafficSchoolException("Qualification for type = " + qualificationDTO.getType() + " and for candidate with id = " + candidateId + " already exists");
        }

        qualification.setType(qualificationDTO.getType());
        qualification.setStartDate(qualificationDTO.getStartDate());
        qualification.setEndDate(qualificationDTO.getEndDate());
        qualification.setTotalHours(qualificationDTO.getTotalHours());
        qualification = qualificationRepository.save(qualification);
        qualificationDTO = modelMapper.map(qualification, QualificationDTO.class);

        return qualificationDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        this.qualificationRepository.deleteById(id);
    }
}
