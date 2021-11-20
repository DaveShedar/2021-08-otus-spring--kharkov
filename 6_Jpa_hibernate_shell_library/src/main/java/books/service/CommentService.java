package books.service;

import books.model.Book;
import books.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAllComment(Book book);

    String addComment(Book book, String text);
}
