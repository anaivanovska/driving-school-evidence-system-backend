package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Indexed
@Entity
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private long id;
    @Column(unique = true)
    private String name;
    private long price;

    public Category() {

    }

    @Override
    public String toString() {
        return name;
    }
}
