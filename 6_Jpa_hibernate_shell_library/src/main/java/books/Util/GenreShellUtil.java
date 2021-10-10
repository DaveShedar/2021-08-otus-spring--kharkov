package books.Util;

import books.dao.GenreDao;
import books.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import java.util.List;

@AllArgsConstructor
@ShellComponent
public class GenreShellUtil {
    private final GenreDao genreDao;
    private final IoStreamHelper streamHelper;
    private Genre currentGenre;

    @ShellMethod(key = {"findAllGenres"}, value = "List all genres")
    List<Genre> findAllGenres() {
        return genreDao.findAll();
    }

    @ShellMethod(key = {"createGenre"}, value = "Create genre")
    String create(@ShellOption(help = "Name of the new genre") final String name) {
        genreDao.save(new Genre(null, name));
        return "Genre " + name + " created";
    }

    @ShellMethod(key = {"specifyGenre"}, value = "Specify genre for next operations")
    public void specify() {
        List<Genre> genreList = genreDao.findAll();
        String message = "Choose the genre by ID from the list:\n" + genreList;
        currentGenre = streamHelper.chooseThing(genreList, message);
    }

    @ShellMethod(key = {"deleteGenre"}, value = "Delete genre")
    @ShellMethodAvailability(value = "isCurrentGenreSpecified")
    String delete() {

        try {
            genreDao.deleteById(currentGenre.getId());
            currentGenre = null;
            return "Genre deleted";
        } catch (Exception e) {
            return "Нельзя оставить книгу без жанра!!!";
        }
    }

    @ShellMethod(key = {"updateGenre"}, value = "Update genre")
    @ShellMethodAvailability(value = "isCurrentGenreSpecified")
    String update(@ShellOption(help = "New name of the genre") final String name) {
        genreDao.save(new Genre(currentGenre.getId(), name));
        currentGenre = null;
        return "Genre updated ";
    }

    private Availability isCurrentGenreSpecified() {
        return currentGenre == null
                ? Availability.unavailable("genre not specified")
                : Availability.available();
    }
}
