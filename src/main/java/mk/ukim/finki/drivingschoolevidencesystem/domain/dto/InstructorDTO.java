package mk.ukim.finki.drivingschoolevidencesystem.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InstructorDTO extends UserDTO {
    private List<String> categories = new ArrayList<>();

    public InstructorDTO() {
        super();
    }
}
