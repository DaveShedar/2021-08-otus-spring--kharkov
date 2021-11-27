package mongodb.shell;

import lombok.RequiredArgsConstructor;
import mongodb.model.Book;
import mongodb.service.IoStreamHelper;
import mongodb.service.LibraryService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import java.util.List;

@RequiredArgsConstructor
@ShellComponent
public class ShellService {

    private final LibraryService libraryService;
    private final IoStreamHelper streamHelper;
    private Book currentBook;

    @ShellMethod(key = "b", value = "get book from DB by Id")
    public void getBookById(@ShellOption({"book_id"}) String bookId) {

        Book book = libraryService.getBookById(bookId);
        System.out.println("Название книги: " + book.getTitle());
        System.out.print("Автор:");
        book.getAuthors().forEach(elm -> {
            System.out.print(" " + elm.getName());
        });
        System.out.println();
        System.out.println("Жанр: " + book.getGenre().getName());
        System.out.println("Комментарии: ");

        try {
            book.getComments().forEach(elm -> {
                System.out.println(elm.getComment());
            });
        } catch (RuntimeException e) {
            System.out.println("отсутствуют!");
        }

    }

    @ShellMethod(key = "c", value = "get book from DB by Id")
    public void createBook(@ShellOption({"book_title"}) String bookTitle,
                           @ShellOption({"book_author"}) String authors,
                           @ShellOption({"book_genre"}) String genre) {
        libraryService.createBook(bookTitle, authors, genre);
        System.out.println("Книга сохранена!");
    }

    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    @ShellMethod(key = "com", value = "add comment to book")
    public void addComment(@ShellOption({"book_comment"}) String comment) {
        libraryService.addComment(comment, currentBook);
        System.out.println("Комментарий сохранен!");
        currentBook = null;
    }

    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    @ShellMethod(key = "del", value = "add comment to book")
    public void deleteBook () {
        libraryService.deleteBook(currentBook.getId());
    }

    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    @ShellMethod(key = "upd", value = "update book")
    public void updateBook (@ShellOption({"book_title"}) String bookTitle,
                            @ShellOption({"book_author"}) String authors,
                            @ShellOption({"book_genre"}) String genre,
                            @ShellOption({"book_comment"}) String comment) {

        libraryService.updateBook(currentBook, bookTitle, authors, genre, comment);
    }

    @ShellMethod(key = {"sb"}, value = "Specify book for update or delete")
    public void specify() {
        List<Book> listBooks = libraryService.findAllBooks();
        String message = "Выберите книгу из списка\n" + listBooks;
        currentBook = streamHelper.chooseThing(listBooks, message);
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }
}
