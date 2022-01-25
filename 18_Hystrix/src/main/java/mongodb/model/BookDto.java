package mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;

    private String title;

    private String authors;

    private String genre;

    private List<Comment> comments;

    public static BookDto toDto(Book book) {
        StringBuilder authorsDto = new StringBuilder();
        book.getAuthors().forEach(author  -> authorsDto.append(author.getName()).append(", "));
        authorsDto.setLength(authorsDto.length() - 2);
        return new BookDto(book.getId(), book.getTitle(), authorsDto.toString(), book.getGenre().getName(), book.getComments());
    }
}
