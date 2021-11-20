package books.Util;

import books.dao.AuthorDao;
import books.dao.BookDao;
import books.dao.GenreDao;
import books.model.Author;
import books.model.Book;
import books.model.Genre;
import lombok.Data;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@ShellComponent
public class BookShellUtil {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IoStreamHelper streamHelper;
    private Book currentBook;

    @ShellMethod(key = "getBookById", value = "get book from DB by Id")
    public Book getBookById(@ShellOption({"book_id"}) long book_id) {
        return bookDao.findById(book_id);
    }

    @ShellMethod(key = "findAllBook", value = "find all books")
    public List<Book> findAllBook() {
        return bookDao.findAll();
    }

    @ShellMethod(key = "insertBook", value = "insert book to db")
    public List<Book> insertBook(@ShellOption(help = "Book title") String title,
                                 @ShellOption(help = "Author divided by comma")String[] authors,
                                 @ShellOption(help = "Boo")String genre) {

        List<Author> authorList = Arrays.stream(authors).map(name -> new Author(null, name)).collect(Collectors.toList());
        bookDao.insert(new Book(null, title, authorList, new Genre(null, genre)));
        return bookDao.findAll();
    }

    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    @ShellMethod(key = {"updateBook"}, value = "update book")
    public String updateBook(@ShellOption(help = "Book title") final String title,
                           @ShellOption(help = "Genre name") final String genreName,
                           @ShellOption(help = "Author names separated by comma") final String[] newAuthors) {

        String book = currentBook.getTitle();
        final Genre genre = currentBook.getGenre().getName().equals(genreName) ? currentBook.getGenre() : new Genre(5L, genreName);
        final Map<String, Author> currentAuthors = currentBook.getAuthors().stream().collect(Collectors.toMap(Author::getName, a -> a));
        final List<Author> authors = Arrays.asList(newAuthors).stream()
                .map(name -> currentAuthors.containsKey(name) ? currentAuthors.get(name) : new Author(null, name))
                .collect(Collectors.toList());
        bookDao.update(new Book(currentBook.getId(), title, authors, genre));
        currentBook = null;
        return "Book " + book + " was successfully updated to " + title;

    }

    @ShellMethod(key = {"specifyBook"}, value = "Specify book for update or delete")
    public void specify() {
        List<Book> listBooks = bookDao.findAll();
        String message = "Choose book by ID from the list:\n" + listBooks;
        currentBook = streamHelper.chooseBook(listBooks, message);
    }

    @ShellMethod(key = {"deleteBook"}, value = "Delete book")
    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    public String delete() {
        String book = currentBook.getTitle();
        bookDao.deleteById(currentBook.getId());
        return "Book " + book + " successfully deleted";
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }
}
