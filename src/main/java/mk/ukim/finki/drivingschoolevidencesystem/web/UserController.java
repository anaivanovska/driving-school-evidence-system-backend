package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCredentials;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    private final String BASE_API_URL = "api/user";

    @PostMapping("login")
    public void login(@RequestBody UserCredentials userCredentials){

    }

    @PostMapping(BASE_API_URL+"/createNew")
    public UserDTO createNew(@RequestBody UserDTO userDTO) {
        return this.userService.createNew(userDTO);
    }

    @PostMapping(BASE_API_URL+"/edit")
    public UserDTO edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }

    @DeleteMapping(BASE_API_URL+"/remove")
    public long remove(@RequestParam long id) {
        userService.remove(id);
        return id;
    }

    @GetMapping(BASE_API_URL + "/byEmail")
    public UserDTO getCandidateWithEmail(@RequestParam String email){
        return this.userService.getByEmail(email);
    }
}
