package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;


@Entity
@Setter
@Getter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String brand;
    @Column(unique = true)
    private String registrationNumber;
    private Date registrationDate;
    private String comment;
    @ManyToOne
    private User instructor;
    @ManyToOne
    private Category category;

    public Vehicle() {

    }

}
