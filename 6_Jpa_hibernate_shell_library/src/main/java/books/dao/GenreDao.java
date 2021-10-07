package books.dao;

import books.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    List<Genre> findAll();

    Genre findByById(Long id);

    Optional<Genre> findByName(String name);

    Genre save(Genre genre);

    void deleteById(Long id);
}
