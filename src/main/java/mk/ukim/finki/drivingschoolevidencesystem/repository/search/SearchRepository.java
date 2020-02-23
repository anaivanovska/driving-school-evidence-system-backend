package mk.ukim.finki.drivingschoolevidencesystem.repository.search;

import java.util.List;

public interface SearchRepository {
    public <T> List<T> searchKeyword(Class<T> entityClass, String keyword, String... fields);
    public <T> List<T> searchPhrase(Class<T> entityClass, String text, String... fields);
}
