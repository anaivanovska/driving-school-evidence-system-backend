package mk.ukim.finki.trafficschoolevidencesystem.init;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "admin")
public class Admin extends User{

    public Admin(){
        super();
    }
}