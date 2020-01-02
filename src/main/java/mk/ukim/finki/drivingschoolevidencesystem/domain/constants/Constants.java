package mk.ukim.finki.drivingschoolevidencesystem.domain.constants;

public class Constants {
    public static final long MILLISECONDS_IN_DAY = 1000*60*60*24;
    public static class Page {
        public static final int START = 0;
        public static final int SIZE = 5;
        public static final String DEFAULT_SORT_PROPERTY = "lastName";
    }

    public static enum Role {
        ADMIN("admin"), CANDIDATE("candidate"), INSTRUCTOR("instructor");

        private String name;

        private Role(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
