package mk.ukim.finki.trafficschoolevidencesystem.domain.dto;

import lombok.Data;

@Data
public class DrivingCourseDTO {
    private long id;
    private String ordinalNumber;
    private VehicleDTO vehicle;

    public DrivingCourseDTO() {

    }
}
