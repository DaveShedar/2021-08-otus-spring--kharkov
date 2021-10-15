package books.service;

import books.model.Book;

import java.util.List;

public interface CommentService {
    List<String> findAllComment(Long bookId);

    String addComment(Book book, String text);
}
