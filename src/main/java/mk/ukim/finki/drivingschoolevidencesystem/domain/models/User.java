package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String embg;
    private String identityCardNumber;
    private String firstName;
    private String lastName;
    private String parentName;
    private String password;
    private String proffession;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String gender;

    public User() {}
}
