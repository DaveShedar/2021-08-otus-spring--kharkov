package books.dao.impl;

import books.dao.GenreDao;
import books.model.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Genre> findAll() {
        return entityManager.createQuery("select genres from Genre genres", Genre.class).getResultList();
    }

    @Override
    public Genre findByById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.name = :name", Genre.class).setParameter("name", name);

        Genre result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }

        return Optional.ofNullable(result);
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
        } else {
            entityManager.merge(genre);
        }
        entityManager.flush();
        return genre;
    }

    @Override
    public void deleteById(Genre genre) {
        entityManager.remove(entityManager.contains(genre) ? genre : entityManager.merge(genre));
        entityManager.flush();
    }
}
