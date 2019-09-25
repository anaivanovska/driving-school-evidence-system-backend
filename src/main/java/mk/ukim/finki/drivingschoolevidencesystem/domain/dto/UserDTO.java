package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private long id;
    private String embg;
    private String identityCardNumber;
    private String firstName;
    private String lastName;
    private String parentName;
    private String password;
    private String proffession;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String gender;
    Set<String> roles;
    public UserDTO() {

    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
