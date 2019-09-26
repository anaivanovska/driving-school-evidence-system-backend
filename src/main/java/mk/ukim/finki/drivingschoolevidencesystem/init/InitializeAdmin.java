package mk.ukim.finki.drivingschoolevidencesystem.init;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@DependsOn("initializeRoles")
@Component
public class InitializeAdmin {
    @Autowired
    private Admin admin;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        User user = userRepository.findUserByEmail(admin.getEmail()).orElse(null);
        if(user == null) {
           String encodedPassword = passwordEncoder.encode(admin.getPassword());
           user = new User();
           user.setEmbg(admin.getEmbg());
           user.setEmail(admin.getEmail());
           user.setPassword(encodedPassword);
           Role admin = roleRepository.findById(Constants.Role.ADMIN.name())
                                    .orElseThrow(() -> new TrafficSchoolException("Role admin not found"));
           user.getRoles().add(admin);
           user = userRepository.save(user);
        }
    }
}
