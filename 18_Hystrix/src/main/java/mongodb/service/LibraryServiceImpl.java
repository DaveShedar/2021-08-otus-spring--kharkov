package mongodb.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import mongodb.model.Author;
import mongodb.model.Book;
import mongodb.model.BookDto;
import mongodb.model.Comment;
import mongodb.model.Genre;
import mongodb.repository.AuthorRepository;
import mongodb.repository.BookRepository;
import mongodb.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")},
            fallbackMethod = "readThisBookWhile")
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addBook(BookDto bookDto) {
        String id = bookDto.getId();
        if(id == null || id.isEmpty()) {
            id = null;
        }

        String title = bookDto.getTitle();
        List<Author> authors = updateAuthor(bookDto.getAuthors());
        Genre genre = updateGenre(bookDto.getGenre());
        List<Comment> comments = bookDto.getComments();

        if (id != null) {

            bookRepository.save(new Book(id, title, authors, genre, comments));
        } else {
            bookRepository.save(new Book(null, title, authors, genre, comments));
        }
    }

    @Override
    public BookDto updateBookForm(String id) {
        Book book = bookRepository.findById(id).orElse(null);

        StringBuilder authors = new StringBuilder();
        book.getAuthors().forEach(author -> authors.append(author.getName() + ","));
        authors.setLength(authors.length() - 1);

        return new BookDto(book.getId(), book.getTitle(), authors.toString(), book.getGenre().getName(), null);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    private List<Author> updateAuthor(String authors) {
        ArrayList<Author> authorList = new ArrayList<>();
        String[] authorArr = authors.split(",");
        List<String> justAuthorNames = new ArrayList<>();
        authorRepository.findAll().forEach(elem -> justAuthorNames.add(elem.getName()));
        Arrays.stream(authorArr).forEach(elem -> {
            if (justAuthorNames.contains(elem)) {
                Author author = authorRepository.getAuthorByName(elem);
                authorList.add(author);

            } else {
                Author author = new Author(null, elem);
                authorRepository.save(author);
                authorList.add(author);
            }
        });
        return authorList;
    }

    private Genre updateGenre(String genre) {
        Genre genreToBook;
        List<String> justNameGenre = new ArrayList<>();
        genreRepository.findAll().forEach(elem -> justNameGenre.add(elem.getName()));
        if (justNameGenre.contains(genre)) {
            genreToBook = genreRepository.findByName(genre);
        } else {
            genreToBook = new Genre(genre);
            genreRepository.save(genreToBook);
        }
        return genreToBook;
    }

    public List<Book> readThisBookWhile() {
        return List.of(new Book("123456", "Пока обрабатываем запрос, просмотрите эту книгу",
                List.of(new Author("2234", "Плеханов")), new Genre("Квантовая физика"), List.of(new Comment("12221", "это стоит почитать"))));

    }


}
