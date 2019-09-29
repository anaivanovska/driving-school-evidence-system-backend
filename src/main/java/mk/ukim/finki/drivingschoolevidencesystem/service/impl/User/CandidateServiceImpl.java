package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CandidateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Candidate;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CandidateRepository;
import mk.ukim.finki.drivingschoolevidencesystem.security.generator.PasswordGenerator;
import mk.ukim.finki.drivingschoolevidencesystem.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("candidateService")
public class CandidateServiceImpl extends UserServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CandidateDTO createNew(CandidateDTO candidateDTO) {
        Candidate candidate = candidateRepository.findByEmbg(candidateDTO.getEmbg());
        Role candidateRole = getRole(Constants.Role.CANDIDATE.name());
        if(candidate == null && candidate.getRoles().contains(candidateRole)) {
            throw new TrafficSchoolException("Candidate with embg = " + candidateDTO.getEmbg() + "already exists." );
        }

        String password = "";
        if(candidate == null) {
            password = passwordGenerator.generateRandomPassword();
            candidate.setPassword(passwordEncoder.encode(password));
        }
        setCandidate(candidate, candidateDTO);
        candidate.getRoles().add(candidateRole);
        candidate = candidateRepository.save(candidate);
        //SEND MAIL
        candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
        return candidateDTO;
    }


    @Transactional
    @Override
    public CandidateDTO edit(CandidateDTO candidateDTO) {
        long id = candidateDTO.getId();
        Candidate candidate = getCandidateWithiId(id);
        setCandidate(candidate, candidateDTO);
        candidate = candidateRepository.save(candidate);
        candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
        return candidateDTO;
    }


    @Transactional
    @Override
    public void remove(long id) {
        Candidate candidate = getCandidateWithiId(id);
        if(candidate.getRoles().size() > 1) {
            candidate.getRoles().remove(getRole(Constants.Role.CANDIDATE.name()));
            candidateRepository.save(candidate);
            return;
        }
        candidateRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<CandidateDTO> getAll(int pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, Constants.Page.SIZE, Sort.by(Constants.Page.DEFAULT_SORT_PROPERTY));
        Page<Candidate> candidatePage = candidateRepository.findAllByRoles_name(Constants.Role.CANDIDATE.name(), pageRequest);
        Page<CandidateDTO> result = modelMapper.map(candidatePage, Page.class);
        return result;
    }

    private void setCandidate(Candidate candidate, CandidateDTO candidateDTO) {
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setParentName(candidateDTO.getParentName());
        candidate.setProffession(candidateDTO.getProffession());
        candidate.setBirthDate(candidateDTO.getBirthDate());
        candidate.setBirthPlace(candidateDTO.getBirthPlace());
        candidate.setAddress(candidateDTO.getAddress());
        candidate.setPhoneNumber(candidateDTO.getPhoneNumber());
        candidate.setGender(candidateDTO.getGender());
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public Candidate getCandidateWithiId(long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("candidate with id = " + id + " does not exist"));
    }


}
