package books.service.impl;

import books.dao.BookDao;
import books.dao.CommentDao;
import books.model.Book;
import books.model.Comment;
import books.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public List<String> findAllComment(Long bookId) {
        List<Comment> listAllComments = commentDao.all().stream().filter(c -> c.getBook().getId().equals(bookId)).collect(Collectors.toList());
        List<String> comments = new ArrayList<>();
        listAllComments.forEach(c -> comments.add(c.getComment()));
        return comments;
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


}
