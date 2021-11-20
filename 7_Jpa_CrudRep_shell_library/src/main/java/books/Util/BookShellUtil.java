package books.Util;

import books.dao.AuthorRepository;
import books.dao.BookRepository;
import books.dao.CommentRepository;
import books.dao.GenreRepository;
import books.model.Author;
import books.model.Book;
import books.model.Genre;
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

    private final IoStreamHelper streamHelper;
    private Book currentBook;

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @ShellMethod(key = "getBookById", value = "get book from DB by Id")
    public Book getBookById(@ShellOption({"book_id"}) long book_id) {
        return bookRepository.findBookById(book_id);
    }

    @ShellMethod(key = "findAllBook", value = "find all books")
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @ShellMethod(key = {"specifyBook"}, value = "Specify book for update or delete")
    public void specify() {
        List<Book> listBooks = bookRepository.findAll();
        String message = "Choose book by ID from the list:\n" + listBooks;
        currentBook = streamHelper.chooseThing(listBooks, message);
    }

    @ShellMethod(key = "insertBook", value = "insert book to db")
    public String insertBook(@ShellOption(help = "Book title") String title,
                                 @ShellOption(help = "Author divided by comma")String[] authors,
                                 @ShellOption(help = "Genre title")String genre) {

        Book book = new Book(null, title, getAuthor(Arrays.asList(authors)), getGenre(genre));
        return "Книга " + bookRepository.save(book).getTitle() + " сохранена";
    }

    @ShellMethod(key = {"updateBook"}, value = "update book")
    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    public String updateBook(@ShellOption(help = "Book title") final String title,
                           @ShellOption(help = "Genre name") final String genreName,
                           @ShellOption(help = "Author names separated by comma") final String[] newAuthors) {

        currentBook.setTitle(title);
        currentBook.setGenre(getGenre(genreName));
        currentBook.setAuthors(getAuthor(Arrays.asList(newAuthors)));
        Book savedBook = bookRepository.save(currentBook);
        currentBook = null;
        return "Книга " + savedBook.getTitle() + " обновлена";
    }

    @ShellMethod(key = {"deleteBook"}, value = "Delete book")
    @ShellMethodAvailability(value = "isCurrentBookSpecified")
    public String delete() {

        String book = currentBook.getTitle();
        commentRepository.deleteById(currentBook.getId());
        bookRepository.deleteById(currentBook.getId());
        currentBook = null;
        return "Книга " + book + " удалена";
    }

    private Availability isCurrentBookSpecified() {
        return currentBook == null
                ? Availability.unavailable("book is not specified")
                : Availability.available();
    }

    private Genre getGenre(String genreName) {
        return genreRepository.findByName(genreName).orElse(new Genre(null, genreName));
    }

    private List<Author> getAuthor(List<String> newAuthors) {

        final Map<String, Author> authorsInDb = authorRepository.findAllByNameSet(newAuthors).stream()
                .collect(Collectors.toMap(Author::getName, Function.identity()));
        return newAuthors.stream()
                .map(name -> authorsInDb.containsKey(name) ? authorsInDb.get(name) : new Author(null, name))
                .collect(Collectors.toList());
    }
}
