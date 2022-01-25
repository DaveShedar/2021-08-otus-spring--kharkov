package mongodb.controller;

import lombok.RequiredArgsConstructor;
import mongodb.model.BookDto;
import mongodb.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/findall")
    public List<BookDto> listBooks() {
        return libraryService.findAllBooks().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@PathVariable(name = "id") String id) {
        libraryService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/findall/{author}")
    public List<BookDto> findAllBookByAuthor(@PathVariable(name = "author") String author) {

        return libraryService.findAllBooks()
                .stream()
                .map(BookDto::toDto)
                .filter(book -> book.getAuthors().contains(author))
                .collect(Collectors.toList());
    }
}
