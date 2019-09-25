package mk.ukim.finki.trafficschoolevidencesystem.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class DriverLicence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String categoryName;
    private LocalDate examinationDate;
    @ManyToOne
    private User owner;

    public DriverLicence() {}
}
