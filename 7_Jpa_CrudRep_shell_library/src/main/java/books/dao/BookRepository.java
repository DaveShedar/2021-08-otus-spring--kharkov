package books.dao;

import books.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);
    List<Book> findAll();
}
