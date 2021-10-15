package books.dao.impl;

import books.dao.AuthorDao;
import books.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
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

    @Override
    public void removeAuthor(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
        entityManager.flush();
    }


}
