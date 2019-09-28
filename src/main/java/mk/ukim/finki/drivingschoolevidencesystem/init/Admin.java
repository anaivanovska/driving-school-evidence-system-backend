package mk.ukim.finki.drivingschoolevidencesystem.init;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "admin")
public class Admin extends User{

    public Admin(){
        super();
    }
}