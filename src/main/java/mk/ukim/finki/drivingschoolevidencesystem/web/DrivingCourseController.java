package mk.ukim.finki.drivingschoolevidencesystem.web;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseInputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.domain.dto.DrivingCourseOutputDTO;
import mk.ukim.finki.drivingschoolevidencesystem.service.DrivingCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivingCourse")
@CrossOrigin(allowedHeaders = "*", exposedHeaders = SecurityConstants.EXPOSED_HEADERS, origins = "*")
public class DrivingCourseController {

    @Autowired
    private DrivingCourseService drivingCourseService;

    @PostMapping("/new/{userId}")
    public long createNew(@RequestBody DrivingCourseInputDTO drivingCourseInputDTO, @PathVariable long userId) {
        return drivingCourseService.createNew(drivingCourseInputDTO, userId);
    }
}
