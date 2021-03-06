package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DriverLicenceDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.DriverLicence;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.DriverLicenceRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.DriverLicenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverLicenceServiceImpl implements DriverLicenceService {
    @Autowired
    private DriverLicenceRepository driverLicenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public DriverLicenceDTO createNew(DriverLicenceDTO driverLicenceDTO, long candidateId) {
        DriverLicence driverLicence = driverLicenceRepository.findByCategoryNameAndOwner_Id(driverLicenceDTO.getCategoryName(), candidateId);
        if(driverLicence != null) {
            throw new TrafficSchoolException("Driver licence for category: " + driverLicenceDTO.getCategoryName() + " and for candidate with id = " + candidateId + " already exists.");
        }
        driverLicence = modelMapper.map(driverLicenceDTO, DriverLicence.class);
        driverLicence.setOwner(findCandidate(candidateId));
        driverLicence = driverLicenceRepository.save(driverLicence);
        driverLicenceDTO = modelMapper.map(driverLicence, DriverLicenceDTO.class);
        return driverLicenceDTO;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected User findCandidate(long id) {
        User candidate = userRepository.findById(id)
                                                .orElseThrow(() -> new TrafficSchoolException("Candidate with id = " + id + " does not exist"));
        return candidate;
    }

    @Transactional
    @Override
    public DriverLicenceDTO edit(DriverLicenceDTO driverLicenceDTO) {
        long id = driverLicenceDTO.getId();
        DriverLicence driverLicence = driverLicenceRepository.findById(id)
                                                        .orElseThrow(() -> new TrafficSchoolException("Driver licence with id = " + id + " does not exist"));
        driverLicence.setCategoryName(driverLicenceDTO.getCategoryName());
        driverLicence.setExaminationDate(driverLicenceDTO.getExaminationDate());
        driverLicence = driverLicenceRepository.save(driverLicence);
        driverLicenceDTO = modelMapper.map(driverLicence, DriverLicenceDTO.class);
        return driverLicenceDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        driverLicenceRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<DriverLicenceDTO> getAllForUser(long userId) {
        return driverLicenceRepository.findAllByOwner_Id(userId)
                                        .stream()
                                        .map(driverLicence -> modelMapper.map(driverLicence, DriverLicenceDTO.class))
                                        .collect(Collectors.toList());
    }
}
