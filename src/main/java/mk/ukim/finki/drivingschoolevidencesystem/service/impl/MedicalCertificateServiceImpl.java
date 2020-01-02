package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DrivingCourse;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.MedicalCertificate;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DrivingCourseRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.MedicalCertificateRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.MedicalCertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicalCertificateServiceImpl implements MedicalCertificateService{
    @Autowired
    private MedicalCertificateRepository medicalCertificateRepository;
    @Autowired
    private DrivingCourseRepository drivingCourseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public MedicalCertificateDTO createNew(MedicalCertificateDTO medicalCertificateDTO, long drivingCourseId) {
        String number = medicalCertificateDTO.getNumber();
        MedicalCertificate medicalCertificate = medicalCertificateRepository.findByNumberAndDrivingCourse_Id(number, drivingCourseId);
        if (medicalCertificate != null) {
            throw new TrafficSchoolException("Medical certficate with number: " + number + " already exists");
        }
        medicalCertificate = modelMapper.map(medicalCertificateDTO, MedicalCertificate.class);
        medicalCertificate.setDrivingCourse(findDrivingCourseById(drivingCourseId));
        medicalCertificate = medicalCertificateRepository.save(medicalCertificate);
        medicalCertificateDTO = modelMapper.map(medicalCertificate, MedicalCertificateDTO.class);

        return medicalCertificateDTO;
    }

    private DrivingCourse findDrivingCourseById(long drivingCourseId) {
        return drivingCourseRepository.findById(drivingCourseId)
                                    .orElseThrow(() -> new TrafficSchoolException("Driving course with id " + drivingCourseId + " does not exist"));
    }
    @Transactional
    @Override
    public MedicalCertificateDTO edit(MedicalCertificateDTO medicalCertificateDTO) {
        long id = medicalCertificateDTO.getId();
        MedicalCertificate medicalCertificate = medicalCertificateRepository.findById(id)
                                                                           .orElseThrow(() -> new TrafficSchoolException("Medical certificate with id: " + id + " does not exist"));
        medicalCertificate.setNumber(medicalCertificateDTO.getNumber());
        medicalCertificate.setIssueDate(medicalCertificateDTO.getIssueDate());
        medicalCertificate.setIssuePlace(medicalCertificateDTO.getIssuePlace());
        medicalCertificate = medicalCertificateRepository.save(medicalCertificate);
        medicalCertificateDTO = modelMapper.map(medicalCertificate, MedicalCertificateDTO.class);
        return medicalCertificateDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        medicalCertificateRepository.deleteById(id);
    }

    @Transactional
    @Override
    public MedicalCertificateDTO getMedicalCertificateForDrivingCourse(long drivingCourseId) {
        MedicalCertificate medicalCertificate = medicalCertificateRepository.findByDrivingCourse_Id(drivingCourseId);
        MedicalCertificateDTO medicalCertificateDTO = modelMapper.map(medicalCertificate, MedicalCertificateDTO.class);
        return medicalCertificateDTO;
    }
}
