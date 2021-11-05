package books.Util;

import books.model.Author;
import books.model.Book;
import books.model.Genre;
import books.service.AuthorService;
import books.service.BookService;
import books.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class BookShellUtil {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final IoStreamHelper streamHelper;
    private Book currentBook;

    @ShellMethod(key = "findAllBook", value = "find all books")
    public List<Book> findAllBook() {
        return bookService.findAllBook();
    }

    @ShellMethod(key = "getBookById", value = "get book from DB by Id")
    public Book getBookById(@ShellOption({"book_id"}) long bookId) {
        return bookService.getBookById(bookId);
    }

    @ShellMethod(key = {"specifyBook"}, value = "Specify book for update or delete")
    public void specify() {
        List<Book> listBooks = bookService.findAllBook();
        String message = "Choose book by ID from the list:\n" + listBooks;
        currentBook = streamHelper.chooseThing(listBooks, message);
    }

    @ShellMethod(key = "insertBook", value = "insert book to db")
    public String insertBook(@ShellOption(help = "Book title") String title,
                             @ShellOption(help = "Author divided by comma")String[] authors,
                             @ShellOption(help = "Genre title")String genre) {

        return bookService.insertBook(new Book(null, title, getAuthor(Arrays.asList(authors)), getGenre(genre), null));
    }

    @ShellMethod(key = {"updateBook"}, value = "update book")
    public String updateBook(@ShellOption(help = "Book title") final String title,
                           @ShellOption(help = "Genre name") final String genreName,
                           @ShellOption(help = "Author names separated by comma") final String[] newAuthors) {

        if(currentBook == null) {
            return "укажите книгу для удаления через команду specifyBook";
        }

        currentBook.setTitle(title);
        currentBook.setGenre(getGenre(genreName));
        currentBook.setAuthors(getAuthor(Arrays.asList(newAuthors)));
        bookService.insertBook(currentBook);
        currentBook = null;
        return "Книга обновлена";
    }

    @ShellMethod(key = {"deleteBook"}, value = "Delete book")
    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    public String delete() {

        String message = bookService.delete(currentBook);
        currentBook = null;
        return message;
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }

    private Genre getGenre(String genreName) {
        return genreService.findByName(genreName);
    }

    private List<Author> getAuthor(List<String> newAuthors) {

        final Map<String, Author> authorsInDb = authorService.findAllByNameSet(newAuthors).stream()
                .collect(Collectors.toMap(Author::getName, Function.identity()));

        return newAuthors.stream()
                .map(name -> authorsInDb.containsKey(name) ? authorsInDb.get(name) : new Author(null, name))
                .collect(Collectors.toList());
    }
}
