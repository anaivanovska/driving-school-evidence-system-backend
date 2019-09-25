package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VehicleDTO implements Serializable {
    private long id;
    private String type;
    private String brand;
    private String registrationNumber;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime registrationDate;
    private String comment;
    private String categoryName;
    private UserDTO instructor;

    public VehicleDTO() {

    }
}
