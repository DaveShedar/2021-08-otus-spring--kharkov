package mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"comments"})
@Document
public class Book {
    @Id
    private String id;
    private String title;

    @DBRef
    private List<Author> authors;

    @DBRef
    private Genre genre;

    @DBRef
    private List<Comment> comments;

}

