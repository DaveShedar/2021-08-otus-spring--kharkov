package books.dao.impl;

import books.dao.CommentDao;
import books.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
//@Transactional
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void deleteByBookId(Long id) {
        entityManager.createQuery("delete from Comment comments where comments.book.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.merge(comment);
        } else {
            entityManager.merge(comment);
        }
        entityManager.flush();
        return comment;
    }

    @Override
    public List<Comment> all() {
        return entityManager.createQuery("select comments from Comment comments").getResultList();
    }
}
