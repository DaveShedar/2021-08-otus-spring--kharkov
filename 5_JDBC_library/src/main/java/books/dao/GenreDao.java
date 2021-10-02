package books.dao;

import books.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findAll();

    Genre findByById(Long id);

    Genre findByName(String name);

    Long insert(Genre genre);

    int update(Genre genre);

    int deleteById(Long id);
}
