package books.dao;

import books.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("select comments from Comment comments where comments.book.id = :id")
    List<Comment> findAllByBookId(@Param("id") Long id);
}
