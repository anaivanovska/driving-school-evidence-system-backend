package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor extends User {
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name="instructor_category",
            joinColumns={@JoinColumn(name="instructor_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="category_id", referencedColumnName="id")})
    private Set<Category> categories = new HashSet<>();
}
