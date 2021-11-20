package books.service.impl;

import books.dao.BookDao;
import books.dao.CommentDao;
import books.model.Book;
import books.model.Comment;
import books.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public List<Comment> findAllComment(Book book) {
        return book.getComments();
    }

    @Transactional
    @Override
    public String addComment(Book book, String text) {
        try {
            if (bookDao.findById(book.getId()) != null) {
                commentDao.save(new Comment(null, text, book));
            }
            return "Комментарий сохранен!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Комментарий не сохранен((((";
    }

    public String deleteComment() {
        return "Комментарий удален";
    }

}
