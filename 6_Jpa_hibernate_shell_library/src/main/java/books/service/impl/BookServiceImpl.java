package books.service.impl;

import books.dao.BookDao;
import books.model.Book;
import books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Transactional
    @Override
    public List<Book> findAllBook() {
        return bookDao.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookDao.findById(bookId);
    }

    @Transactional
    @Override
    public String insertBook(Book book) {

        bookDao.save(book);
        return "книга сохранена";
    }

    @Transactional
    @Override
    public String delete(Book book) {
        bookDao.delete(book);
        return "Книга удалена";
    }
}
