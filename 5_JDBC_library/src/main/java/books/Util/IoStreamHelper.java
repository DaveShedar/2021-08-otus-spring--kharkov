package books.Util;

import books.model.Author;
import books.model.Book;
import books.model.Genre;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class IoStreamHelper {
    private final Scanner scanner = new Scanner(System.in);

    public Book chooseBook(List<Book> bookList, String message) {
        System.out.println(message);
        int chooseBookId = scanner.nextInt() - 1;
        System.out.println("You choose book: " + bookList.get(chooseBookId).getTitle());
        return bookList.get(chooseBookId);
    }

    public Author chooseAuthor(List<Author> authorList, String message) {
        System.out.println(message);
        int chooseAuthorId = scanner.nextInt() - 1;
        System.out.println("You choose author: " + authorList.get(chooseAuthorId).getName());
        return authorList.get(chooseAuthorId);
    }

    public Genre chooseGenre(List<Genre> genreList, String message) {
        System.out.println(message);
        int chooseGenreId = scanner.nextInt() - 1;
        System.out.println("You choose genre: " + genreList.get(chooseGenreId).getName());
        return genreList.get(chooseGenreId);
    }

    public void print(String string) {
        System.out.println(string);
    }
}
