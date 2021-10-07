package books.Util;

import books.dao.BookRepository;
import books.dao.CommentRepository;
import books.model.Book;
import books.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellUtil {

    private final IoStreamHelper streamHelper;
    private Book currentBook;

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @ShellMethod(key = {"findAllComment"}, value = "List comments of the book")
    @ShellMethodAvailability("isCurrentBookSpecified")
    void findAllComment() {
        commentRepository.findAllByBookId(currentBook.getId()).forEach(comment -> System.out.println(comment.getComment()));
        currentBook = null;
    }

    @ShellMethod(key = "addComment", value = "Add comment")
    @ShellMethodAvailability("isCurrentBookSpecified")
    String addComment(@ShellOption(help = "Text of Comment") String text) {

        try {
            if(bookRepository.findById(currentBook.getId()) != null) {
                commentRepository.save(new Comment(null, text, currentBook));
            }
            currentBook = null;
            return "Комментарий сохранен!";

        } catch (Exception e){
            e.printStackTrace();
        }
        return "Комментарий не сохранен((((";
    }

    @ShellMethod(key = {"specifyComment"}, value = "Specify book for update or delete")
    public void specifyComment() {
        List<Book> listBooks = bookRepository.findAll();
        String message = "Choose book by ID from the list:\n" + listBooks;
        currentBook = streamHelper.chooseThing(listBooks, message);
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }
}
