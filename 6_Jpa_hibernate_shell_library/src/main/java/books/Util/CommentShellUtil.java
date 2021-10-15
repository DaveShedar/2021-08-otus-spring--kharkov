package books.Util;

import books.dao.BookDao;
import books.dao.CommentDao;
import books.model.Book;
import books.model.Comment;
import books.service.BookService;
import books.service.CommentService;
import books.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellUtil {

    private final BookService bookService;
    private final CommentService commentService;
    private final IoStreamHelper streamHelper;
    private Book currentBook;

    @ShellMethod(key = {"findAllComment"}, value = "List comments of the book")
    @ShellMethodAvailability("isCurrentBookSpecified")
    public List<String> findAllComment() {
        return commentService.findAllComment(currentBook.getId());
    }

    @ShellMethod(key = "addComment", value = "Add comment")
    @ShellMethodAvailability("isCurrentBookSpecified")
    public String addComment(@ShellOption(help = "Text of Comment") String text) {
        String message = commentService.addComment(currentBook, text);
        currentBook = null;
        return message;
    }

    @ShellMethod(key = {"specifyComment"}, value = "Specify book for update or delete")
    public void specifyComment() {
        List<Book> listBooks = bookService.findAllBook();
        String message = "Choose book by ID from the list:\n" + listBooks;
        currentBook = streamHelper.chooseThing(listBooks, message);
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }
}
