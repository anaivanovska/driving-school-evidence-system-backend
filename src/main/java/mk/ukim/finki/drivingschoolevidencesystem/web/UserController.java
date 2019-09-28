package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCredentials;
import mk.ukim.finki.drivingschoolevidencesystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public void login(@RequestBody UserCredentials userCredentials){

    }

    @GetMapping("api/user/byEmail")
    public UserDTO getCandidateWithEmail(@RequestParam String email){
        return this.userService.getByEmail(email);
    }
}
