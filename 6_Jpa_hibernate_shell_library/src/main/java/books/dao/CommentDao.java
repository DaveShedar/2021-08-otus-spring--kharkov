package books.dao;

import books.model.Book;
import books.model.Comment;

import java.util.List;

public interface CommentDao {
    void deleteByBookId(Long id);

    Comment save(Comment comment);

    List<Comment> all();
}
