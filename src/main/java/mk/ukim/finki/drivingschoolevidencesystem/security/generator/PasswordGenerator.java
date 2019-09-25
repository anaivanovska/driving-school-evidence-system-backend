package mk.ukim.finki.drivingschoolevidencesystem.security.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordGenerator {

    public String generateRandomPassword() {
        String password = UUID.randomUUID().toString();
        return password;
    }
}
