package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
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
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
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
    public UserDTO createNew(UserDTO userDTO) {
        User user = userRepository.findByEmbg(userDTO.getEmbg());        if(user == null) {
            user = new User();
            setUserData(user, userDTO);
            String password = passwordGenerator.generateRandomPassword();
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            //SEND MAIL
            userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }

        throw new TrafficSchoolException("User with embg = " + userDTO.getEmbg() + "already exists." );
    }


    @Transactional
    @Override
    public UserDTO edit(UserDTO userDTO) {
        long id = userDTO.getId();
        User user = getById(id);
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
        Page<User> users = userRepository.findAllByRoles_name(roleName, pageable);
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
        user.setRoles(getRoles(userDTO.getRoles()));
    }

    public Set<Role> getRoles(Set<String> roles) {
        Set<Role> userRoles = new HashSet<>();
        for(String name : roles) {
            userRoles.add(getRole(name));
        }
        return userRoles;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Role getRole(String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new TrafficSchoolException("Role with name: " + name + " does not exist"));

        return role;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TrafficSchoolException("User with id = " + id + " does not exist"));
    }

    @Transactional
    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new TrafficSchoolException("User with email " + email + " does not exist"));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}

