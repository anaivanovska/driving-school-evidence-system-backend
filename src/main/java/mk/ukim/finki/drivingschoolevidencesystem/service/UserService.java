package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;

public interface UserService {
    UserDTO getByEmail(String email);
    UserDTO edit(UserDTO userDTO);
    UserDTO createNew(UserDTO userDTO);
    void remove(long id);

}
