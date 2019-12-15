package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.CategoryDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCredentials;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping(BASE_API_URL+"/new")
    public UserDTO createNew(@RequestBody UserDTO userDTO) {
        return this.userService.createNew(userDTO);
    }

    @PostMapping(BASE_API_URL+"/edit")
    public UserDTO edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }


    @GetMapping(BASE_API_URL + "/byEmail")
    public UserDTO getCandidateWithEmail(@RequestParam String email){
        return this.userService.getByEmail(email);
    }

    @GetMapping(BASE_API_URL + "/all/{roleName}")
    public Page<UserDTO> getUsersWithRole(@PathVariable String roleName, @PageableDefault(size = Constants.Page.SIZE, page = Constants.Page.START) Pageable pageable) {
        return userService.getAllWithRole(roleName, pageable);
    }

    @DeleteMapping(BASE_API_URL+"/remove")
    public long remove(@RequestParam long id) {
        userService.remove(id);
        return id;
    }
}
