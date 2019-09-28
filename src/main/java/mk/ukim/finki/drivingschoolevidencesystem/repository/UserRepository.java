package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends CrudRepository<T, Long> {
    T findByEmbg(String embg);
    Optional<T> findByEmail(String email);
    Optional<T> findByIdAndRoles_name(long id, String name);
}
