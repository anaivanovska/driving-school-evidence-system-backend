package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverLicenceDTO {
    private long id;
    private String categoryName;
    private LocalDate examinationDate;

    public DriverLicenceDTO() {

    }
}
