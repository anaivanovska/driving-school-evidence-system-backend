package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.InstructorDTO;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.InstructorRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.security.generator.PasswordGenerator;
import mk.ukim.finki.drivingschoolevidencesystem.service.CandidateService;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("instructorService")
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public InstructorDTO createNew(InstructorDTO instructorDTO) {
//        Instructor instructor =
//        Candidate candidate = candidateRepository.findByEmbg(candidateDTO.getEmbg());
//        if(candidate == null) {
//            String password = passwordGenerator.generateRandomPassword();
//            setCandidateData(candidate, candidateDTO);
//            candidate.setPassword(passwordEncoder.encode(password));
//            candidate = candidateRepository.save(candidate);
//            //SEND MAIL
//            candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
//            return candidateDTO;
//        }
//
//        throw new TrafficSchoolException("Candidate with embg = " + candidateDTO.getEmbg() + "already exists." );
        return null;
    }

    @Override
    public InstructorDTO edit(InstructorDTO instructorDTO) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public Collection getAll() {
        return null;
    }
}
