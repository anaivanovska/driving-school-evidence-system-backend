package mk.ukim.finki.drivingschoolevidencesystem.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Indexed
@AnalyzerDef(name = "fieldAnalyzer",
        filters = { @TokenFilterDef(factory = LowerCaseFilterFactory.class)},
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class))
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    @Column(unique = true)
    private String embg;
    private String identityCardNumber;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    private String firstName;
    @Field
    @Analyzer(definition = "fieldAnalyzer")
    private String lastName;
    private String parentName;
    private String password;
    private String profession;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String birthPlace;
    private String address;
    private String phoneNumber;
    private String gender;
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<UserRole> userRoles;

    public User() {}
}
