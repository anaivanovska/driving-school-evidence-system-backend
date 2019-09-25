package mk.ukim.finki.trafficschoolevidencesystem.domain.models;

import lombok.Data;

@Data
public class UserCredentials {
    private String username;
    private String password;

    public UserCredentials() {}
}
