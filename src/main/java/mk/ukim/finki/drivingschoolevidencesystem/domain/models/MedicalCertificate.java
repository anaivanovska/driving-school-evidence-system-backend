package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class MedicalCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String number;
    private LocalDate issueDate;
    private String issuePlace;
    @OneToOne
    private DrivingCourse drivingCourse;


    public MedicalCertificate() {

    }
}
