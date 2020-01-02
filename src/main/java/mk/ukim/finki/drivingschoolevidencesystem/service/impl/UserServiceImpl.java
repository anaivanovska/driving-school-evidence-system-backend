package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserRole;
import mk.ukim.finki.drivingschoolevidencesystem.repository.InstructorCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.security.generator.PasswordGenerator;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
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
    public UserDTO createNew(UserDTO userDTO, String role) {
        User user = userRepository.findByEmbg(userDTO.getEmbg());
        if(user == null) {
            user = new User();
            setUserData(user, userDTO);
            String password = passwordGenerator.generateRandomPassword();
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            //SEND MAIL
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(findRoleByName(role));
            userRoleRepository.save(userRole);
            userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }

        throw new TrafficSchoolException("User with embg = " + userDTO.getEmbg() + "already exists." );
    }

    @Transactional
    @Override
    public UserDTO addRole(String email, String roleName) {
        User user = findByEmail(email);
        Role role = findRoleByName(roleName);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected Role findRoleByName(String roleName) {
        return roleRepository.findById(roleName)
                            .orElseThrow(() -> new TrafficSchoolException("Role with name " + roleName + " does not exist"));
    }

    @Transactional
    @Override
    public UserDTO edit(UserDTO userDTO) {
        long id = userDTO.getId();
        User user = findById(id);
        setUserData(user, userDTO);
        user = userRepository.save(user);
        userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Transactional
    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<UserDTO> getAllWithRole(String roleName, Pageable pageable) {
        Page<User> users = userRoleRepository.findAllByRole_Name(roleName, pageable)
                                                    .map(user -> user.getUser());
        Page<UserDTO> userDtos =  users.map(user -> modelMapper.map(user, UserDTO.class));
        return userDtos;
    }

    public void setUserData(User user, UserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        user.setEmbg(userDTO.getEmbg());
        user.setIdentityCardNumber(userDTO.getIdentityCardNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setParentName(userDTO.getParentName());
        user.setProffession(userDTO.getProffession());
        user.setBirthDate(userDTO.getBirthDate());
        user.setBirthPlace(userDTO.getBirthPlace());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDTO getById(long id) {
        User user = findById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(propagation =  Propagation.MANDATORY)
    protected User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("User with id = " + id + " does not exist"));
    }
    @Transactional(propagation = Propagation.MANDATORY)
    protected User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new TrafficSchoolException("User with email " + email + " does not exist"));
    }
    @Transactional
    @Override
    public UserDTO getByEmail(String email) {
        User user = findByEmail(email);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}

