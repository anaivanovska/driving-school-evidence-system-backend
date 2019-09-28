package mk.ukim.finki.drivingschoolevidencesystem.service.impl;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;

public interface UserService {
    UserDTO getByEmail(String email);
}
