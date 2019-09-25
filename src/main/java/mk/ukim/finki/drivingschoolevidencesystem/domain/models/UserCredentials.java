package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Data;

@Data
public class UserCredentials {
    private String username;
    private String password;

    public UserCredentials() {}
}
