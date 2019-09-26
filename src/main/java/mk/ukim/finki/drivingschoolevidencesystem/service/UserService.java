package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;

public interface UserService {
    UserDTO createNew(UserDTO userDTO);

    UserDTO edit(UserDTO userDTO);

    void remove(long id);

    UserDTO getUserByEmail(String email);
}
