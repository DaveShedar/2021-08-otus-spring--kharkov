package books.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Author {
    private Long id;
    private String name;
}
