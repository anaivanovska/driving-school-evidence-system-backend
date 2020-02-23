package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DrivingCourseInputDTO {
    private long id;
    private String ordinalNumber;
    private long lecturerId;
    private long vehicleId;
    private MedicalCertificateDTO medicalCertificate;
    private Set<QualificationDTO> qualifications = new HashSet<>();
    private Set<TrialTestDTO> trialTests = new HashSet<>();

    public DrivingCourseInputDTO() {

    }
}
