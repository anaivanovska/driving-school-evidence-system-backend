package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;


@Indexed
@Entity
@Setter
@Getter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private long id;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    private String type;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    private String brand;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
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
