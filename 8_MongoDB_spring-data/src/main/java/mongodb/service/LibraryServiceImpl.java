package mongodb.service;

import lombok.RequiredArgsConstructor;
import mongodb.exception.BookNotFoundException;
import mongodb.model.Author;
import mongodb.model.Book;
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

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Книга не найдена по данным параметрам поиска"));
    }

    @Override
    public void createBook(String bookTitle, String authors, String genre) {
        bookRepository.save(new Book(null, bookTitle, updateAuthor(authors), updateGenre(genre), null));
    }

    @Override
    public void updateBook (Book book, String bookTitle, String authors, String genre, String comments) {
        book.setTitle(bookTitle);
        book.setAuthors(updateAuthor(authors));
        book.setGenre(updateGenre(genre));
        book.setComments(updateComment(book, comments));

        bookRepository.save(book);

    }

    public List<Author> updateAuthor(String authors) {
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

    public Genre updateGenre(String genre) {
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

    public List<Comment> updateComment(Book book, String comment) {
        List<Comment> commentListFromDB = book.getComments();
        List<Comment> newCommentList = new ArrayList<>();

        Comment newComment = new Comment(null, comment);

        if (commentListFromDB.isEmpty()) {
            newCommentList.add(newComment);
            return newCommentList;
        } else {
            commentListFromDB.add(newComment);
            return commentListFromDB;
        }
    }

    @Override
    public void addComment(String comment, Book book) {
        book.setComments(updateComment(book, comment));
        bookRepository.save(book);

    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

}
