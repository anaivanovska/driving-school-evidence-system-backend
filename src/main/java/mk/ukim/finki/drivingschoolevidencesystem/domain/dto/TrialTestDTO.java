package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrialTestDTO {
    private long id;
    private String testNumber;
    private LocalDate examinationDate;
    private int points;

    public TrialTestDTO() {

    }
}
