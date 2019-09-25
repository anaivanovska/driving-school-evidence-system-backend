package mk.ukim.finki.trafficschoolevidencesystem.service.impl;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.UserRole;
import mk.ukim.finki.trafficschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRoleRepository;
import mk.ukim.finki.trafficschoolevidencesystem.security.generator.PasswordGenerator;
import mk.ukim.finki.trafficschoolevidencesystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public UserDTO createNew(UserDTO userDTO) {
        User user = userRepository.findUserByEmbg(userDTO.getEmbg());
        if(user == null) {
            user = modelMapper.map(userDTO, User.class);
            String password = passwordGenerator.generateRandomPassword();
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            //SEND EMAIL
            for (String role : userDTO.getRoles()) {
                addNewRoleForUser(user, role);
            }
            userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }

        throw new TrafficSchoolException("User with embg = " + userDTO.getEmbg() + "already exists." );
    }
//    long id = user.getId();
//    List<String> userRoles = getUserRoles(id);
//        if(!userRoles.equals(userDTO.getRoles())) {
//        user = userRepository.save(user);
//        for (String role : userDTO.getRoles()) {
//            if (!userRoles.contains(role)) {
//                addNewRoleForUser(user, role);
//            }
//        }
//        userDTO = modelMapper.map(user, UserDTO.class);
//        return userDTO;
//    }
    @Transactional(propagation = Propagation.MANDATORY.MANDATORY)
    public void addNewRoleForUser(User user, String roleName) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        Role role = getRole(roleName);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Role getRole(String name) {
        return roleRepository.findById(name)
                            .orElseThrow(() -> new TrafficSchoolException("Role with name = " + name + " not found"));
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<String> getUserRoles(long id) {
        List<UserRole> existingUserRoles = userRoleRepository.findByUser_Id(id);
        List<String> roleNames = new ArrayList<>();
        for(UserRole userRole : existingUserRoles){
            roleNames.add(userRole.getRole().getName());
        }
        return roleNames;
    }
}
