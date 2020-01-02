package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserRole;

public interface UserRoleService {
    boolean checkExistenceOfUserWithEmailAndRole(String email, String role);

}
