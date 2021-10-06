package books.dao.impl;

import books.dao.CommentDao;
import books.model.Comment;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Data
@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Comment> findAllBookById(Long id) {
        return entityManager.createQuery("select comments from Comment comments where comments.book.id = :id").setParameter("id", id).getResultList();
    }

    @Transactional
    @Override
    public void deleteByBookId (Long id) {
        entityManager.createQuery("delete from Comment comments where comments.book.id = :id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        if(comment.getId() == null) {
            entityManager.merge(comment);
        } else {
            entityManager.merge(comment);
        }
        entityManager.flush();
        return comment;
    }
}
