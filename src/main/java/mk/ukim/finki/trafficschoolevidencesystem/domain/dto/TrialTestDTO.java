package mk.ukim.finki.trafficschoolevidencesystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrialTestDTO {
    private long id;
    private String testNumber;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate examinationDate;
    private int points;

    public TrialTestDTO() {

    }
}
