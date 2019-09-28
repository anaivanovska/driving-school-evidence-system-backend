package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Candidate;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CandidateRepository extends UserRepository<Candidate> {

}
