package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private RoleRepository roleRepository;
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
        if(candidate == null) {
            String password = passwordGenerator.generateRandomPassword();
            setCandidateData(candidate, candidateDTO);
            candidate.setPassword(passwordEncoder.encode(password));
            candidate = candidateRepository.save(candidate);
            //SEND MAIL
            candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
            return candidateDTO;
        }

        throw new TrafficSchoolException("Candidate with embg = " + candidateDTO.getEmbg() + "already exists." );
    }


    @Transactional
    @Override
    public CandidateDTO edit(CandidateDTO candidateDTO) {
        long id = candidateDTO.getId();
        Candidate candidate = getCandidateWithiId(id);
        setCandidateData(candidate, candidateDTO);
        candidate = candidateRepository.save(candidate);
        candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
        return candidateDTO;
    }


    @Transactional
    @Override
    public void remove(long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public Collection<CandidateDTO> getAll() {
        return null;
    }

    private void setCandidateData(Candidate candidate, CandidateDTO candidateDTO) {
        candidate.setFirstName(candidateDTO.getFirstName());
        candidate.setLastName(candidateDTO.getLastName());
        candidate.setParentName(candidateDTO.getParentName());
        candidate.setProffession(candidateDTO.getProffession());
        candidate.setBirthDate(candidateDTO.getBirthDate());
        candidate.setBirthPlace(candidateDTO.getBirthPlace());
        candidate.setAddress(candidateDTO.getAddress());
        candidate.setPhoneNumber(candidateDTO.getPhoneNumber());
        candidate.setGender(candidateDTO.getGender());
        candidate.setRoles(getRoles(candidateDTO.getRoles()));
    }

    private Set<Role> getRoles(Set<String> roles) {
        Set<Role> candidateRoles = new HashSet<>();
        for(String name : roles) {
            candidateRoles.add(getRole(name));
        }
        return candidateRoles;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Role getRole(String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new TrafficSchoolException("Role with name: " + name + " does not exist"));

        return role;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Candidate getCandidateWithiId(long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("candidate with id = " + id + " does not exist"));
    }


}
