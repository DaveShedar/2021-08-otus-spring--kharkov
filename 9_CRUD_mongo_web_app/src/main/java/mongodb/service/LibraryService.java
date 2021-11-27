package mongodb.service;

import mongodb.model.Author;
import mongodb.model.Book;
import mongodb.model.BookDto;

import java.util.List;

public interface LibraryService {
    List<Book> findAllBooks();
    void deleteBook(String id);

    void addBook(BookDto bookDto);

    void updateBook(BookDto bookDto);

    BookDto updateBookForm(String id);
}
