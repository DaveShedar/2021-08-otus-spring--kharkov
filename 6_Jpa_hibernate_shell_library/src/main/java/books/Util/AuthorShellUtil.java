package books.Util;

import books.dao.AuthorDao;
import books.model.Author;
import lombok.Data;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@Data
@ShellComponent
@ShellCommandGroup("Authors")
public class AuthorShellUtil {

    private final AuthorDao authorDao;
    private final IoStreamHelper streamHelper;
    private Author currentAuthor;

    @ShellMethod(key = {"findAllAuthors"}, value = "Get list all authors")
    List<Author> findAllAuthors() {
        return authorDao.findAll();
    }

    @ShellMethod(key = {"createAuthor"}, value = "Create author")
    String create(@ShellOption(help = "Create the name of new author") final String name) {
        authorDao.save(new Author(null, name));
        return "Author " + name + " created";
    }

    @ShellMethod(key = {"specifyAuthor"}, value = "Specify author for next operations")
    public void specify() {
        List<Author> authorList = authorDao.findAll();
        String message = "Choose the name of author by ID from the list:\n" + authorList;
        currentAuthor = streamHelper.chooseThing(authorList, message);
    }

    @ShellMethod(key = {"updateAuthor"}, value = "Update author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    String update(@ShellOption(help = "New author name") final String name) {
        authorDao.save(new Author(currentAuthor.getId(), name));
        currentAuthor = null;
        return "Author updated ";
    }

    @ShellMethod(key = {"deleteAuthor"}, value = "Delete author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    String deleteAuthor() {
        try{
            authorDao.deleteById(currentAuthor.getId());
            currentAuthor = null;
            return "Author deleted";
        } catch (Exception e) {
            return "Нельзя оставить книгу без автора!!!";
        }

    }

    private Availability isCurrentAuthorSpecified() {
        return currentAuthor == null
                ? Availability.unavailable("author not specified")
                : Availability.available();
    }
}
