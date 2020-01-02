package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    List<UserRole> findAllByUser_Email(String email);
    UserRole findByUser_EmailAndRole_Name(String email, String namel);
    Page<UserRole> findAllByRole_Name(String name, Pageable pageable);
}
