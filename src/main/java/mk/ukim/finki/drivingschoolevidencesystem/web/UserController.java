package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.UserDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createNew")
    public UserDTO createNew(@RequestBody UserDTO userDTO) {
        return this.userService.createNew(userDTO);
    }

    @PostMapping("/edit")
    public UserDTO edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }

    @DeleteMapping("/remove")
    public long remove(@RequestParam long id) {
        this.userService.remove(id);
        return id;
    }

    @GetMapping("/byEmail")
    public UserDTO getUserWithEmail(@RequestParam String email){
        return this.userService.getUserByEmail(email);
    }
}
