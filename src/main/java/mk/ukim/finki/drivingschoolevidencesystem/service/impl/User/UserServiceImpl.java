package mk.ukim.finki.drivingschoolevidencesystem.service.impl.User;

import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository<User> userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new TrafficSchoolException("User with email "  + email + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }
}
