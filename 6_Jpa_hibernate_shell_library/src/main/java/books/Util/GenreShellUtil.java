package books.Util;

import books.model.Genre;
import books.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;


@RequiredArgsConstructor
@ShellComponent
public class GenreShellUtil {

    private final GenreService genreService;
    private final IoStreamHelper streamHelper;
    private Genre currentGenre;

    @ShellMethod(key = {"findAllGenres"}, value = "List all genres")
    public List<Genre> findAllGenres() {
        return genreService.findAllGenres();
    }

    @ShellMethod(key = {"createGenre"}, value = "Create genre")
    public String create(@ShellOption(help = "Name of the new genre") final String name) {
        return genreService.saveGenre(new Genre(null, name));
    }

    @ShellMethod(key = {"specifyGenre"}, value = "Specify genre for next operations")
    public void specify() {
        List<Genre> genreList = genreService.findAllGenres();
        String message = "Choose the genre by ID from the list:\n" + genreList;
        currentGenre = streamHelper.chooseThing(genreList, message);
    }

    @ShellMethod(key = {"deleteGenre"}, value = "Delete genre")
    @ShellMethodAvailability(value = "isCurrentGenreSpecified")
    public String delete() {
        return genreService.delete(currentGenre);
    }

    @ShellMethod(key = {"updateGenre"}, value = "Update genre")
    @ShellMethodAvailability(value = "isCurrentGenreSpecified")
    String update(@ShellOption(help = "New name of the genre") final String name) {
        String message = genreService.update(currentGenre.getId(), name);
        currentGenre = null;
        return message;

    }

    private Availability isCurrentGenreSpecified() {
        return currentGenre == null
                ? Availability.unavailable("genre not specified")
                : Availability.available();
    }


}
