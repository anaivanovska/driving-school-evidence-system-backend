package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class TrialTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String testNumber;
    private LocalDate examinationDate;
    private int points;
    @OneToOne
    private DrivingCourse drivingCourse;

    public TrialTest() {}
}
