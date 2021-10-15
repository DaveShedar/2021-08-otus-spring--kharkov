package books.service.impl;

import books.dao.AuthorDao;
import books.model.Author;
import books.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }

    @Transactional
    @Override
    public String create(String name) {
        authorDao.save(new Author(null, name));
        return "Author " + name + " created";
    }

    @Transactional
    @Override
    public String update(Long authorId, String name) {
        authorDao.save(new Author(authorId, name));
        return "Author updated ";
    }

    @Transactional
    @Override
    public String deleteAuthor(Author author) {
        try{
            authorDao.removeAuthor(author);
            return "Author deleted";
        } catch (Exception e) {
            return "Нельзя оставить книгу без автора!!!";
        }
    }

    @Override
    public List<Author> findAllByNameSet(List<String> newAuthors) {
        return authorDao.findAllByNameSet(newAuthors);
    }
}
