package mk.ukim.finki.drivingschoolevidencesystem.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import mk.ukim.finki.drivingschoolevidencesystem.domain.constants.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_NAME);
        if(header==null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request ) {
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if(token != null) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            Claims claims = Jwts.parser()
                                .setSigningKey(SecurityConstants.SECRET)
                                .parseClaimsJws(token)
                                .getBody();
            String username = claims.getSubject();
            if(username != null){
                List<HashMap<String, String>> authoritiesMap = (List) claims.get("authorities");
                List<GrantedAuthority>  authorities = new ArrayList<>();
                for(HashMap<String, String> map : authoritiesMap) {
                    for(String value: map.values()) {
                        authorities.add(new SimpleGrantedAuthority(value));
                    }
                }

                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
            return null;
        }
        return null;
    }


}
