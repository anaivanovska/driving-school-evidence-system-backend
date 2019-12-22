package mk.ukim.finki.drivingschoolevidencesystem.init;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeRoles {



    @PostConstruct
    public void init() {
    }
}
