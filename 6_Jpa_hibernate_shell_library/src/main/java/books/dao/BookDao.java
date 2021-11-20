package books.dao;

import books.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Book book);
}
