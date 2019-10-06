package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmbg(String embg);
    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndRoles_name(long id, String name);
    Page<User> findAllByRoles_name(String name, Pageable pageable);
}
