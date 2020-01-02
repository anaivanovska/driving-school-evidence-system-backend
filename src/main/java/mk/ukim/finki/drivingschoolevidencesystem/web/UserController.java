package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCredentials;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserRoleService;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;


    private final String BASE_API_URL = "api/user";

    @PostMapping("login")
    public void login(@RequestBody UserCredentials userCredentials){
    }


    @PostMapping(BASE_API_URL+"/new/{role}")
    public ResponseEntity createNew(@RequestBody UserDTO userDTO, @PathVariable String role) {
        if (userRoleService.checkExistenceOfUserWithEmailAndRole(userDTO.getEmail(), role)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        UserDTO user;
        HttpStatus status;
        try {
            user = userService.addRole(userDTO.getEmail(), role);
            status = HttpStatus.ACCEPTED;

        } catch (TrafficSchoolException e) {
            user = this.userService.createNew(userDTO, role);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity(user, status);
    }

    @PostMapping(BASE_API_URL+"/edit")
    public UserDTO edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }


    @GetMapping(BASE_API_URL + "/byEmail")
    public UserDTO getUserWithEmail(@RequestParam String email){
        return this.userService.getByEmail(email);
    }

    @GetMapping(BASE_API_URL + "/all/{roleName}")
    public Page<UserDTO> getUsersWithRole(@PathVariable String roleName, @PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        return userService.getAllWithRole(roleName, pageable);
    }

    @DeleteMapping(BASE_API_URL+"/remove/{id}")
    public long remove(@PathVariable long id) {
        userService.remove(id);
        return id;
    }

    @GetMapping(BASE_API_URL+"/{id}")
    public UserDTO getUserByID(@PathVariable long id) {
        return userService.getById(id);
    }
}
