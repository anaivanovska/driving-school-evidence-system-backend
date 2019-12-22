package mk.ukim.finki.drivingschoolevidencesystem.security.service;

import mk.ukim.finki.drivingschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.User;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import mk.ukim.finki.drivingschoolevidencesystem.domain.models.UserCategory;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserCategoryRepository;
import mk.ukim.finki.drivingschoolevidencesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserCategory> userDetails = userCategoryRepository.findAllByUser_Email(username);

        if (userDetails.size() == 0) {
            throw new TrafficSchoolException("User with username: " + username + " not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserCategory userRole : userDetails) {
            authorities.add(new SimpleGrantedAuthority(SecurityConstants.GRANTED_AUTHORITY_PREFIX + userRole.getRole()));
        }

        User user = userDetails.get(0).getUser();
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}
