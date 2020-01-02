package mk.ukim.finki.drivingschoolevidencesystem.init;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeRoles {
    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void init() {
        for(Constants.Role role : Constants.Role.values()) {
            roleRepository.save(new Role(role.getName()));
        }
    }
}
