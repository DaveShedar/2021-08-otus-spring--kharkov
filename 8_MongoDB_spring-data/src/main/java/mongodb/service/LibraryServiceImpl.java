package mongodb.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mongodb.exception.BookNotFoundException;
import mongodb.model.Author;
import mongodb.model.Book;
import mongodb.model.Comment;
import mongodb.model.Genre;
import mongodb.repository.AuthorRepository;
import mongodb.repository.BookRepository;
import mongodb.repository.CommentRepository;
import mongodb.repository.GenreRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Книга не найдена по данным параметрам поиска"));
    }

    public void createBook(String bookTitle, String authors, String genre) {
        bookRepository.save(new Book(null, bookTitle, updateAuthor(authors), updateGenre(genre), null));
    }

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
        commentRepository.save(newComment);
        Comment justSaveComment = commentRepository.findByComment(comment);

        if (commentListFromDB.isEmpty()) {
            newCommentList.add(justSaveComment);
            return newCommentList;
        } else {
            commentListFromDB.add(justSaveComment);
            return commentListFromDB;
        }
    }


    public void addComment(String comment, Book book) {
        book.setComments(updateComment(book, comment));
        bookRepository.save(book);

    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

}
