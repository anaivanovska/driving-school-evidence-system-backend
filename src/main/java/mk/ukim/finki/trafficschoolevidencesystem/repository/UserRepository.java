package mk.ukim.finki.trafficschoolevidencesystem.repository;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByIdAndRolesContains_name(long id, String name);
    User findUserByEmbg(String embg);
    @Query("select r.role_id from user u join user_role ur on :id = ur.user_id")
    Set<String> findUserRoles(@Param("id") long id);
}
