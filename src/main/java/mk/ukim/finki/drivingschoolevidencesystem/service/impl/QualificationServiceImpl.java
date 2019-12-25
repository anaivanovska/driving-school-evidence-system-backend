package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.QualificationDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Qualification;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.QualificationRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.QualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public QualificationDTO createNew(QualificationDTO qualificationDTO, long drivingCourseId) {
        Qualification qualification = qualificationRepository.findByTypeAndDrivingCourse_Id(qualificationDTO.getType(),
                                                                                        drivingCourseId);
        if(qualification != null) {
            throw new TrafficSchoolException("Qualification of type = " + qualificationDTO.getType() + " for driving course = " + drivingCourseId + " already exists");
        }

        setQualification(qualification, qualificationDTO);
        qualification.setDrivingCourse(findDrivingCourseById(drivingCourseId));
        qualification = qualificationRepository.save(qualification);
        qualificationDTO = modelMapper.map(qualification, QualificationDTO.class);
        return qualificationDTO;
    }

    private void setQualification(Qualification qualification, QualificationDTO qualificationDTO) {
        qualification.setType(qualificationDTO.getType());
        qualification.setStartDate(qualificationDTO.getStartDate());
        qualification.setEndDate(qualificationDTO.getEndDate());
        qualification.setTotalHours(qualificationDTO.getTotalHours());
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public DrivingCourse findDrivingCourseById(long drivingCourseId) {
        DrivingCourse drivingCourse = drivingCourseRepository.findById(drivingCourseId)
                .orElseThrow(() -> new TrafficSchoolException("Driving course  with id = " + drivingCourseId + " does not exist"));
        return drivingCourse;
    }

    @Transactional
    @Override
    public QualificationDTO edit(QualificationDTO qualificationDTO) {
        long id = qualificationDTO.getId();
        Qualification qualification = this.qualificationRepository.findById(id)
                                                                .orElseThrow(() -> new TrafficSchoolException("Qualification with id = " + id + " does not exist"));

        setQualification(qualification, qualificationDTO);
        qualification = qualificationRepository.save(qualification);
        qualificationDTO = modelMapper.map(qualification, QualificationDTO.class);
        return qualificationDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        this.qualificationRepository.deleteById(id);
    }

    @Override
    public List<QualificationDTO> getAllQualificationsForDrivingCourse(long drivingCourseId) {

        return qualificationRepository.findByDrivingCourse_Id(drivingCourseId)
                                       .stream()
                                       .map(qualification -> modelMapper.map(qualification, QualificationDTO.class))
                                       .collect(Collectors.toList());
    }
}
