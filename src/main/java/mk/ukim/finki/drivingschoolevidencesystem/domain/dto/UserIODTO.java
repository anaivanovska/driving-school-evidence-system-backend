package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserIODTO {
    private UserDTO user;
    private List<String> categories;

    public UserIODTO(){}
}
