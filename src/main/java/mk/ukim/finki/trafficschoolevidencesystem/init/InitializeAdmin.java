package mk.ukim.finki.trafficschoolevidencesystem.init;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeAdmin {
    @Autowired
    private Admin admin;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        User user = userRepository.findUserByEmail(admin.getEmail()).orElse(null);
        if(user == null) {
           String encodedPassword = passwordEncoder.encode(admin.getPassword());
           user = new User();
           user.setEmbg(admin.getEmbg());
           user.setEmail(admin.getEmail());
           user.setPassword(encodedPassword);
           user.setRole(admin.getRole());
           userRepository.save(user);
        }
    }
}
