package books.dao.impl;

import books.dao.BookDao;
import books.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Book findById(Long id) {
        TypedQuery<Book> typedQuery = entityManager.createQuery("select books from Book books where books.id = :id", Book.class).setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = entityManager.createQuery("select books from Book books join fetch books.genre", Book.class);
        return typedQuery.getResultList();
    }


    @Override
    @Transactional
    public Book save(Book book) {
        if(book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
        entityManager.flush();
        return book;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Book book where book.id = :id").setParameter("id", id).executeUpdate();
        entityManager.flush();
    }
}
