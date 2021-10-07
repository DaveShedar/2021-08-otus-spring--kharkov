package books.dao;

import books.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findAllBookById(Long id);

    void deleteByBookId(Long id);

    Comment save(Comment comment);
}
