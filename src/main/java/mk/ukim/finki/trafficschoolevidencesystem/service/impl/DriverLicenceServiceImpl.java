package mk.ukim.finki.trafficschoolevidencesystem.service.impl;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.DriverLicenceDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.DriverLicence;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.repository.DriverLicenceRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.trafficschoolevidencesystem.service.DriverLicenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public User findCandidate(long id) {
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
}
