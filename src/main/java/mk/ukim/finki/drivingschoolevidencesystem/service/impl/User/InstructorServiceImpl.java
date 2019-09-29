package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CandidateDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.InstructorDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Candidate;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Category;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Instructor;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.repository.CategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.InstructorRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.security.generator.PasswordGenerator;
import mk.ukim.finki.drivingschoolevidencesystem.service.CandidateService;
import mk.ukim.finki.drivingschoolevidencesystem.service.InstructorService;
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

import java.util.Collection;

@Service("instructorService")
public class InstructorServiceImpl extends UserServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public InstructorDTO createNew(InstructorDTO candidateDTO) {
        Instructor instructor = instructorRepository.findByEmbg(candidateDTO.getEmbg());
        Role instructorRole = getRole(Constants.Role.INSTRUCTOR.name());
        if (instructor == null && instructor.getRoles().contains(instructorRole)) {
            throw new TrafficSchoolException("Instructor with embg = " + candidateDTO.getEmbg() + "already exists.");
        }

        String password = "";
        if (instructor == null) {
            password = passwordGenerator.generateRandomPassword();
            instructor.setPassword(passwordEncoder.encode(password));
        }
        setInstructor(instructor, candidateDTO);
        instructor.getRoles().add(instructorRole);
        instructor = instructorRepository.save(instructor);
        //SEND MAIL
        candidateDTO = modelMapper.map(instructor, InstructorDTO.class);
        return candidateDTO;
    }


    @Transactional
    @Override
    public InstructorDTO edit(InstructorDTO instructorDTO) {
        long id = instructorDTO.getId();
        Instructor instructor = getInstructorWithiId(id);
        setInstructor(instructor, instructorDTO);
        instructor = instructorRepository.save(instructor);
        instructorDTO = modelMapper.map(instructor, InstructorDTO.class);
        return instructorDTO;
    }


    @Transactional
    @Override
    public void remove(long id) {
        Instructor instructor = getInstructorWithiId(id);
        if (instructor.getRoles().size() > 1) {
            instructor.getRoles().remove(getRole(Constants.Role.INSTRUCTOR.name()));
            for(Category category : instructor.getCategories()) {
              //DELETE DATA FROM JOIN 
            }

            instructorRepository.save(instructor);
            return;
        }
        instructorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<InstructorDTO> getAll(int pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, Constants.Page.SIZE, Sort.by(Constants.Page.DEFAULT_SORT_PROPERTY));
        Page<Instructor> candidatePage = instructorRepository.findAllByRoles_name(Constants.Role.INSTRUCTOR.name(), pageRequest);
        Page<InstructorDTO> result = modelMapper.map(candidatePage, Page.class);
        return result;
    }

    private void setInstructor(Instructor instructor, InstructorDTO instructorDTO) {
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setParentName(instructorDTO.getParentName());
        instructor.setProffession(instructorDTO.getProffession());
        instructor.setBirthDate(instructorDTO.getBirthDate());
        instructor.setBirthPlace(instructorDTO.getBirthPlace());
        instructor.setAddress(instructorDTO.getAddress());
        instructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        instructor.setGender(instructorDTO.getGender());
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public Instructor getInstructorWithiId(long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("Instructor with id = " + id + " does not exist"));
    }
}
