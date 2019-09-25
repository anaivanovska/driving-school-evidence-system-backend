package mk.ukim.finki.drivingschoolevidencesystem;

import mk.ukim.finki.drivingschoolevidencesystem.init.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Admin.class)
public class DrivingSchoolEvidenceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivingSchoolEvidenceSystemApplication.class, args);
	}

}
