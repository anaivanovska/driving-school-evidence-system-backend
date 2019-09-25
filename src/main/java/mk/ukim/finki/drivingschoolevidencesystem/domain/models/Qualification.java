package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private long totalHours;
    @ManyToOne
    private DrivingCourse drivingCourse;

    public Qualification() {

    }
}
