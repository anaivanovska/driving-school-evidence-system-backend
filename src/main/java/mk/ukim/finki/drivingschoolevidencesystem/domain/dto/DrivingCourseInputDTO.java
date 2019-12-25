package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

@Data
public class DrivingCourseInputDTO {
    private String ordinalNumber;
    private long lecturerId;
    private long vehicleId;
    private String categoryName;

    public DrivingCourseInputDTO() {

    }
}
