package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class VehicleDTO implements Serializable {
    private long id;
    private String type;
    private String brand;
    private String registrationNumber;
    private Date registrationDate;
    private String comment;
    private String categoryName;
    private long instructorId;

    public VehicleDTO() {

    }
}
