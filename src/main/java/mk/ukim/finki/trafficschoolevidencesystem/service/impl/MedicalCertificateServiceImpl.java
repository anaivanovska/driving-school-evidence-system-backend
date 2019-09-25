package mk.ukim.finki.trafficschoolevidencesystem.service.impl;

import mk.ukim.finki.trafficschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.MedicalCertificateDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.MedicalCertificate;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.repository.MedicalCertificateRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.trafficschoolevidencesystem.service.MedicalCertificateService;
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
        User candidate = userRepository.findUserByIdAndRolesContains_name(id, Constants.Role.CANDIDATE.name())
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
