package books.dao.impl;

import books.dao.AuthorDao;
import books.model.Author;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Data
@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("select author from Author author", Author.class).getResultList();
    }

    @Override
    public List<Author> findAllByNameSet(List<String> names) {
        return entityManager.createQuery("select author from Author author where author.name in :names", Author.class)
                .setParameter("names", names)
                .getResultList();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if(author.getId() == null) {
            entityManager.merge(author);
        } else {
            entityManager.merge(author);
        }
        entityManager.flush();
        return author;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Author author where author.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.flush();
    }
}
