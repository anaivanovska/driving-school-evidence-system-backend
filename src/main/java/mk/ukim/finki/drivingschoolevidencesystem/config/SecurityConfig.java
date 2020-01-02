package mk.ukim.finki.drivingschoolevidencesystem.config;

import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.Constants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.security.filters.JWTAuthenticationFilter;
import mk.ukim.finki.drivingschoolevidencesystem.security.filters.JWTAuthorizationFilter;
import mk.ukim.finki.drivingschoolevidencesystem.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/api/category/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/user/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/instructorCategory/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/drivingCourse/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/medicalCertificate/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/qualification/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/trialTest/*").hasRole(Constants.Role.ADMIN.getName())
                .antMatchers("/api/driverLicence/*").hasRole(Constants.Role.ADMIN.getName())
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManagerBean()))
                .addFilter(new JWTAuthenticationFilter(authenticationManagerBean()))
                .exceptionHandling()
                .authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

