package mongodb.repository;

import mongodb.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
    Comment findByComment(String comment);

}
