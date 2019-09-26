package mk.ukim.finki.drivingschoolevidencesystem.config;

import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return new ModelMapper();
    }
}
