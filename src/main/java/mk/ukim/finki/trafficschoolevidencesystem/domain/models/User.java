package mk.ukim.finki.trafficschoolevidencesystem.domain.models;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
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
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String gender;

    public User() {}
}
