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
import mongodb.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class MongoDatabaseChangelog {

    @ChangeSet(order = "000", id = "dropDb", author = "kharkov", runAlways = true)
    public void dropDb(MongoDatabase database) {
    }

    @ChangeSet(order = "000", id = "initBooks", author = "kharkov", runAlways = true)
    public void initBooks(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository) {
    }
}
