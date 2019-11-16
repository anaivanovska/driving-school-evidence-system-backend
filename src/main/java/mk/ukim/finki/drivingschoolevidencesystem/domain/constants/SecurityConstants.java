package mk.ukim.finki.drivingschoolevidencesystem.domain.constants;

public class SecurityConstants {
    public static final String SECRET= "Anaiw292_djwfb782";
    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String ALLOWED_HEADERS = "*";
    public static final String ALLOWED_ORIGINS ="*";
    public static final String EXPOSED_HEADERS = "Authorization, Content-Type, Expires, Last-modified, Access-Control-Allow-Origin, Authorities";
    public static final String ALLOWED_METHODS = "*";
    public static final String GRANTED_AUTHORITY_PREFIX = "ROLE_";
    public static final String AUTHORITIES_HEADER = "Authorities";

}
