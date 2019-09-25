package mk.ukim.finki.trafficschoolevidencesystem.repository;

import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    UserRole findByUser_IdAndRole_Name(long id, String name);
    List<UserRole> findByUser_Id(long id);
}

