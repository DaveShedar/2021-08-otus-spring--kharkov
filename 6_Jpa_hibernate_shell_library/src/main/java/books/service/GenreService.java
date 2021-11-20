package books.service;

import books.model.Genre;
import java.util.List;

public interface GenreService {

    String saveGenre(Genre genre);

    List<Genre> findAllGenres();

    String delete(Genre genre);

    String update(Long id, String name);

    Genre findByName(String genreName);
}
