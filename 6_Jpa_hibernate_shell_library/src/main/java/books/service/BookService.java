package books.service;

import books.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBook();

    Book getBookById(Long bookId);

    String insertBook(Book book);

    String delete(Long bookId);
}
