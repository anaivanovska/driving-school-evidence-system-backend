package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.MedicalCertificate;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.MedicalCertificateRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.MedicalCertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicalCertificateServiceImpl implements MedicalCertificateService{
    @Autowired
    private MedicalCertificateRepository medicalCertificateRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public MedicalCertificateDTO createNew(MedicalCertificateDTO medicalCertificateDTO, long candidateId) {
        String number = medicalCertificateDTO.getNumber();
        MedicalCertificate medicalCertificate = medicalCertificateRepository.findByNumberAndCandidate_Id(number, candidateId);
        if(medicalCertificate != null) {
            throw new TrafficSchoolException("Medical certficate with number: " + number +" for candidate: " + candidateId + " already exists");
        }
        medicalCertificate = modelMapper.map(medicalCertificateDTO, MedicalCertificate.class);
        medicalCertificate.setCandidate(findCandidate(candidateId));
        medicalCertificate = medicalCertificateRepository.save(medicalCertificate);
        medicalCertificateDTO = modelMapper.map(medicalCertificate, MedicalCertificateDTO.class);

        return medicalCertificateDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User findCandidate(long id) {
        User candidate = userRepository.findUserByIdAndRoles_name(id, Constants.Role.CANDIDATE.name())
                                        .orElseThrow(() -> new TrafficSchoolException("Candidate with id = " + id + " does not exist"));
        return candidate;
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
}
