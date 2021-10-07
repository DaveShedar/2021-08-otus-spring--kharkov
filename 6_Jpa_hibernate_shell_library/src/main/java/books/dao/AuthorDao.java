package books.dao;

import books.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();

    List<Author> findAllByNameSet(List<String> names);

    Author save(Author author);

    void deleteById(Long id);
}
