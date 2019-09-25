package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
    private LocalDateTime registrationDate;
    private String comment;
    @ManyToOne
    private User instructor;
    @ManyToOne
    private Category category;

    public Vehicle() {

    }

    private LocalDateTime getNextRegistrationDate() {
        int registrationYear = registrationDate.getYear();
        System.out.println("YEAR: " +registrationDate);
        LocalDateTime nextRegistrationDate = registrationDate.withYear(registrationYear+1);
        return nextRegistrationDate;
    }
    public long getDaysUntilRegistration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRegistrationDate = this.getNextRegistrationDate();
        long nowMillis = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        long nextRegistrationDateMillis = nextRegistrationDate.toInstant(ZoneOffset.UTC).toEpochMilli();
        long timeLeftMillis = nextRegistrationDateMillis - nowMillis;
        long daysLeft = timeLeftMillis / Constants.MILLISECONDS_IN_DAY;
        return daysLeft;

    }
}
