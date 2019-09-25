package mk.ukim.finki.trafficschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DrivingCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ordinalNumber;
    @ManyToOne
    private User candidate;
    @ManyToOne
    private Vehicle vehicle;

    public DrivingCourse() {

    }
}
