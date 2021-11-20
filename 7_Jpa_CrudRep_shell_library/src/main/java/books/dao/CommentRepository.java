package books.dao;

import books.model.Comment;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByBook_Id(Long id);
}
