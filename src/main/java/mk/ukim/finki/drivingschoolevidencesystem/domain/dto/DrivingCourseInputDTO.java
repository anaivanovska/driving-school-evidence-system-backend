package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

@Data
public class DrivingCourseInputDTO {
    private long id;
    private String ordinalNumber;
    private long lecturerId;
    private long vehicleId;

    public DrivingCourseInputDTO() {

    }
}
