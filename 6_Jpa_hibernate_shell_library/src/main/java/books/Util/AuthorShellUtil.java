package books.Util;

import books.model.Author;
import books.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@RequiredArgsConstructor
@ShellComponent
@ShellCommandGroup("Authors")
public class AuthorShellUtil {

    private final AuthorService authorService;
    private final IoStreamHelper streamHelper;
    private Author currentAuthor;

    @ShellMethod(key = {"findAllAuthors"}, value = "Get list all authors")
    public List<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @ShellMethod(key = {"createAuthor"}, value = "Create author")
    public String create(@ShellOption(help = "Create the name of new author") final String name) {
        return authorService.create(name);
    }

    @ShellMethod(key = {"specifyAuthor"}, value = "Specify author for next operations")
    public void specify() {
        List<Author> authorList = authorService.findAllAuthors();
        String message = "Choose the name of author by ID from the list:\n" + authorList;
        currentAuthor = streamHelper.chooseThing(authorList, message);
    }

    @ShellMethod(key = {"updateAuthor"}, value = "Update author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    public String update(@ShellOption(help = "New author name") final String name) {
        String message = authorService.update(currentAuthor.getId(), name);
        currentAuthor = null;
        return message;
    }

    @ShellMethod(key = {"deleteAuthor"}, value = "Delete author")
    @ShellMethodAvailability(value = "isCurrentAuthorSpecified")
    public String deleteAuthor() {
        String message = authorService.deleteAuthor(currentAuthor);
        currentAuthor = null;
        return message;
    }

    private Availability isCurrentAuthorSpecified() {
        return currentAuthor == null
                ? Availability.unavailable("author not specified")
                : Availability.available();
    }
}
