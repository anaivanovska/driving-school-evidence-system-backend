package mk.ukim.finki.trafficschoolevidencesystem.web;

import mk.ukim.finki.trafficschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.UserCredentials;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class LoginController {

    @PostMapping("login")
    public void login(@RequestBody UserCredentials userCredentials){

    }

}
