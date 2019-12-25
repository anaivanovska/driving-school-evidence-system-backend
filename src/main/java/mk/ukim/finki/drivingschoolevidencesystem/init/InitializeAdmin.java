package mk.ukim.finki.drivingschoolevidencesystem.init;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
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
    private UserCategoryRepository userCategoryRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        User user = (User) userRepository.findByEmail(admin.getEmail()).orElse(null);
        if(user == null) {
           String encodedPassword = passwordEncoder.encode(admin.getPassword());
           user = new User();
           user.setEmbg(admin.getEmbg());
           user.setFirstName(admin.getFirstName());
           user.setLastName(admin.getLastName());
           user.setEmail(admin.getEmail());
           user.setPassword(encodedPassword);

           user = (User) userRepository.save(user);
           UserCategory userCategory = new UserCategory();
           userCategory.setUser(user);
           userCategory.setRole(Constants.Role.ADMIN.name());
           userCategoryRepository.save(userCategory);
        }
    }
}
