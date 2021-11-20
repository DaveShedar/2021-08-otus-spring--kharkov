package mongodb.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import mongodb.model.Author;
import mongodb.model.Book;
import mongodb.model.Comment;
import mongodb.model.Genre;
import mongodb.repository.AuthorRepository;
import mongodb.repository.BookRepository;
import mongodb.repository.CommentRepository;
import mongodb.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class MongoDatabaseChangelog {

    @ChangeSet(order = "000", id = "dropDb", author = "kharkov", runAlways = true)
    public void dropDb(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "000", id = "initBooks", author = "kharkov", runAlways = true)
    public void initBooks(AuthorRepository authorRepository, CommentRepository commentRepository, GenreRepository genreRepository,
                          BookRepository bookRepository) {
        List<Author> authorList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();

        authorList.add(new Author("1", "Пушкин"));
        authorList.add(new Author("2", "Лермонтов"));
        authorList.add(new Author("3", "Толстой"));

        commentList.add(new Comment("1", "Oчень интересная книга"));
        commentList.add(new Comment("2", "Советую!!!!!!!"));
        commentList.add(new Comment("3", "В топку(((((((((((((((((((((("));

        Genre genre1 = new Genre("1", "Ужасы");
        Genre genre2 = new Genre("2", "Сказка");

        Book book1 = new Book("1", "Название книги 1",
                authorList,
                genre2,
                commentList);

        Book book2 = new Book("2", "Название книги 2",
                authorList,
                genre1,
                commentList);

        bookList.add(book1);
        bookList.add(book2);

        authorRepository.saveAll(authorList);
        commentRepository.saveAll(commentList);
        genreRepository.save(genre1);
        genreRepository.save(genre2);
        bookRepository.saveAll(bookList);
    }
}
