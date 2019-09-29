package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository<User> userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new TrafficSchoolException("User with email "  + email + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Role getRole(String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new TrafficSchoolException("Role with name: " + name + " does not exist"));

        return role;
    }
}
