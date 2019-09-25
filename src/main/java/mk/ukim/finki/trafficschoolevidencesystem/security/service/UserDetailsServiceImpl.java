package mk.ukim.finki.trafficschoolevidencesystem.security.service;

import mk.ukim.finki.trafficschoolevidencesystem.domain.exceptions.TrafficSchoolException;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.Role;
import mk.ukim.finki.trafficschoolevidencesystem.domain.models.User;
import mk.ukim.finki.trafficschoolevidencesystem.repository.UserRepository;
import mk.ukim.finki.trafficschoolevidencesystem.domain.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new TrafficSchoolException("User with username: " + username + " not found"));
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(SecurityConstants.GRANTED_AUTHORITY_PREFIX + role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}
