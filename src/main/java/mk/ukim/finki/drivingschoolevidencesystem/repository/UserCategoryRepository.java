package mk.ukim.finki.drivingschoolevidencesystem.repository;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    UserCategory findByUser_IdAndCategory_NameAndRole(long userId, String categoryName, String role);
    List<UserCategory> findAllByUser_IdAndRole(long id, String role);
    Page<UserCategory> findAllByRole(String role, Pageable pageable);
    List<UserCategory> findAllByUser_Email(String email);
    List<UserCategory> findAllByRole(String role);
}