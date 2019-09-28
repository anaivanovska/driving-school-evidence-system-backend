package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String gender;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_name", referencedColumnName="name")})
    private Set<Role> roles = new HashSet<>();

    public User() {}
}
