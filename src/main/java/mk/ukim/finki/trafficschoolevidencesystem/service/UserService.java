package mk.ukim.finki.trafficschoolevidencesystem.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.dto.UserDTO;

public interface UserService {
    UserDTO createNew(UserDTO userDTO);
}
