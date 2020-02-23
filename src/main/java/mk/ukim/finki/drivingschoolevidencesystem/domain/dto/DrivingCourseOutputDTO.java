package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DrivingCourseOutputDTO {
    private long id;
    private String ordinalNumber;
    private UserDTO lecturer;
    private VehicleDTO vehicle;
    private MedicalCertificateDTO medicalCertificate;
    private List<QualificationDTO> qualifications = new ArrayList<>();
    private List<TrialTestDTO> trialTests =  new ArrayList<>();

    public DrivingCourseOutputDTO() {

    }
}
