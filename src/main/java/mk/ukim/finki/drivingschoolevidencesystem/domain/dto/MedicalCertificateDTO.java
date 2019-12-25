package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalCertificateDTO {
    private long id;
    private String number;
    private LocalDate issueDate;
    private String issuePlace;
}
