package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name;
    private long price;
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
    private Set<Instructor> instructors = new HashSet<>();

    public Category() {

    }

    @Override
    public String toString() {
        return name;
    }
}
