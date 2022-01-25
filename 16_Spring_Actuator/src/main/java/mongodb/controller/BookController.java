package mongodb.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mongodb.model.BookDto;
import mongodb.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final LibraryService libraryService;
//    private Counter visitCounter;
//
//    public BookController(MeterRegistry registry) {
//        visitCounter = Counter.builder("visit_counter")
//                .description("Количество посещений веб-приложения")
//                .register(registry);
//    }

    @GetMapping("/admin/book-list")
    public String listBooks(Model model) {
        model.addAttribute("books", libraryService.findAllBooks());
        return "book-list";
    }

    @GetMapping("/user/book-list-userview")
    public String listBooksUserView(Model model) {
        model.addAttribute("books", libraryService.findAllBooks());
        return "book-list-userview";
    }

    @GetMapping("/admin/book/{id}")
    public String deleteBook(@PathVariable(name = "id") String id) {
        libraryService.deleteBook(id);
        return "redirect:/admin/book-list";
    }

    @GetMapping("/admin/getForm/{id}")
    public String getForm(@PathVariable (name = "id") String id, Model model) {
        model.addAttribute("bookDto", libraryService.updateBookForm(id));
        model.addAttribute("authorsList", libraryService.findAllAuthors());
        return "add-update-book";
    }

    @GetMapping("/admin/getForm")
    public String getForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("authorsList", libraryService.findAllAuthors());
        return "add-update-book";
    }

    @PostMapping("/admin/updatebook")
    public String updateBook(BookDto bookDto) {
        libraryService.addBook(bookDto);
        return "redirect:/admin/book-list";
    }

}
