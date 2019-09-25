package mk.ukim.finki.trafficschoolevidencesystem;

import mk.ukim.finki.trafficschoolevidencesystem.init.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Admin.class)
public class TrafficSchoolEvidenceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficSchoolEvidenceSystemApplication.class, args);
	}

}
