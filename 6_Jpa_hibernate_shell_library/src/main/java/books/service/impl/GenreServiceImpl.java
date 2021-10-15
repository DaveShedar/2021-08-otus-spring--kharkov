package books.service.impl;

import books.dao.GenreDao;
import books.model.Genre;
import books.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Transactional
    @Override
    public String saveGenre(Genre genre) {
        genreDao.save(genre);
        return "жанр " + genre.getName() + " сохранен";
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreDao.findAll();
    }

    @Transactional
    @Override
    public String delete(Genre genre) {
        try {
            genreDao.deleteById(genre.getId());
            return "Genre deleted";
        } catch (Exception e) {
            return "Нельзя оставить книгу без жанра!!!";
        }
    }

    @Transactional
    @Override
    public String update(Long id, String name) {
        genreDao.save(new Genre(id, name));
        return "Genre updated ";
    }

    @Override
    public Genre findByName(String genreName) {
        return genreDao.findByName(genreName).orElse(new Genre(null, genreName));
    }


}
