package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalCertificateDTO {
    private long id;
    private String number;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate issueDate;
    private String issuePlace;
}
