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
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    T findByEmbg(String embg);
    Optional<T> findByEmail(String email);
    Optional<T> findByIdAndRoles_name(long id, String name);
    Page<T> findAllByRoles_name(String name, Pageable pageable);
}
