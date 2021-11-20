package mongodb.service;

import mongodb.model.Author;
import mongodb.model.Book;

import java.util.List;

public interface LibraryService {
    Book getBookById(String id);
    void createBook(String bookTitle, String authors, String genre);
    void updateBook (Book book, String bookTitle, String authors, String genre, String comments);
    void addComment(String comment, Book book);
    List<Book> findAllBooks();
    void deleteBook(String id);
}
