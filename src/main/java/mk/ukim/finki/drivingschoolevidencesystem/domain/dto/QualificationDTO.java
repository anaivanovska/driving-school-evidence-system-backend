package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class QualificationDTO {
    private long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private long totalHours;

    public QualificationDTO() {

    }
}
