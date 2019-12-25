package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DrivingCourseOutputDTO {
    private long id;
    private String ordinalNumber;
    private UserDTO lecturer;
    private VehicleDTO vehicle;
    private CategoryDTO categoryDTO;
    private Set<QualificationDTO> qualifications = new HashSet<>();
    private TrialTestDTO trialTest;

    public DrivingCourseOutputDTO() {

    }
}
