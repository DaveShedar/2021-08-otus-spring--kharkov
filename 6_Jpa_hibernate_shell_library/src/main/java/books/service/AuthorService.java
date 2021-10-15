package books.service;

import books.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();

    String create(String name);

    String update(Long authorId, String name);

    String deleteAuthor(Author author);

    List<Author> findAllByNameSet(List<String> newAuthors);
}
