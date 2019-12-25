package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class DrivingCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String ordinalNumber;
    @ManyToOne
    private User lecturer;
    @ManyToOne
    private User candidate;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Category category;

    public DrivingCourse() {

    }
}
