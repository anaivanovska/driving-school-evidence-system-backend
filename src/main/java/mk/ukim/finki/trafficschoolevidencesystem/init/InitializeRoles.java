package mk.ukim.finki.trafficschoolevidencesystem.init;

import mk.ukim.finki.trafficschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.trafficschoolevidencesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitializeRoles {


    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void init() {
        for(Constants.Role role : Constants.Role.values()) {
            roleRepository.save(new Role(role.name()));
        }
    }
}
