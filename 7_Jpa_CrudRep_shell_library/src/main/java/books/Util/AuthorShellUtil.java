package books.Util;

import books.dao.AuthorRepository;
import books.model.Author;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IoStreamHelper streamHelper;
    private Author currentAuthor;
    private final AuthorRepository authorRepository;

    @ShellMethod(key = {"findAllAuthors"}, value = "Get list all authors")
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @ShellMethod(key = {"specifyAuthor"}, value = "Specify author for next operations")
    public void specify() {
        List<Author> authorList = authorRepository.findAll();
        String message = "Choose the name of author by ID from the list:\n" + authorList;
        currentAuthor = streamHelper.chooseThing(authorList, message);
    }

    @ShellMethod(key = {"deleteAuthor"}, value = "Delete author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    String deleteAuthor() {

        try{
            authorRepository.deleteById(currentAuthor.getId());
            currentAuthor = null;
            return "Author deleted";
        } catch (Exception e) {
            return "Нельзя оставить книгу без автора!!!";
        }

    }

    @ShellMethod(key = {"createAuthor"}, value = "Create author")
    String create(@ShellOption(help = "Create the name of new author") final String name) {
        return "Author " + authorRepository.save(new Author(null, name)).getName() + " created";
    }

    @ShellMethod(key = {"updateAuthor"}, value = "Update author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    String update(@ShellOption(help = "New author name") final String name) {
        authorRepository.save(new Author(currentAuthor.getId(), name));
        currentAuthor = null;
        return "Author updated ";
    }

    private Availability isCurrentAuthorSpecified() {
        return currentAuthor == null
                ? Availability.unavailable("author not specified")
                : Availability.available();
    }
}
