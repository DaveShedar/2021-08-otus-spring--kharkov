package books.dao.impl;

import books.dao.BookDao;
import books.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = entityManager.createQuery("select books from Book books", Book.class);
        return typedQuery.getResultList();
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
        entityManager.flush();
        return book;
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
        entityManager.flush();
    }
}
