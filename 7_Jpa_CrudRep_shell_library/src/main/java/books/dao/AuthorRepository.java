package books.dao;

import books.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    @Query("select author from Author author where author.name in :names")
    Optional<Author> findAllByNameSet(@Param("names") List<String> names);

    void deleteById(Long id);
}
