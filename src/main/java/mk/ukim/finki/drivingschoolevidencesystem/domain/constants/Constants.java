package mk.ukim.finki.drivingschoolevidencesystem.domain.constants;

public class Constants {
    public static long MILLISECONDS_IN_DAY = 1000*60*60*24;

    public static class Page {
        public static final int START = 0;
        public static final int SIZE = 10;
        public static final String DEFAULT_SORT_PROPERTY = "lastName";
    }

    public static enum Role {
        ADMIN, CANDIDATE, INSTRUCTOR
    }
}
