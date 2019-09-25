package mk.ukim.finki.trafficschoolevidencesystem.security.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordGenerator {

    public String generateRandomPassword() {
        String password = UUID.randomUUID().toString();
        return password;
    }
}
