package books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private Genre genre;
}
