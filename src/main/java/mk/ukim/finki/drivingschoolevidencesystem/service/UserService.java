package mk.ukim.finki.drivingschoolevidencesystem.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO getByEmail(String email);
    UserDTO edit(UserDTO userDTO);
    UserDTO createNew(UserDTO userDTO, String role);
    UserDTO addRole(String email, String role);
    void remove(long id);
    Page<UserDTO> getAllWithRole(String roleName, Pageable pageable);
    UserDTO getById(long id);
    Page<UserDTO> search(String value, Pageable pageable);

}
