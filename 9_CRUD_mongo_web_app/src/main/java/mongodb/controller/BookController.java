package mongodb.controller;

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

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", libraryService.findAllBooks());
        return "book-list";
    }

    @GetMapping("/book/{id}")
    public String deleteBook(@PathVariable(name = "id") String id) {
        libraryService.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(BookDto bookDto) {
        libraryService.addBook(bookDto);
        return "redirect:/";
    }

    @GetMapping("/update-book/{id}")
    public String updateBookForm(@PathVariable (name = "id") String id, Model model) {
        model.addAttribute("bookDto",libraryService.updateBookForm(id));
        return "update-book";
    }

    @PostMapping("/updatebook")
    public String updateBook(BookDto bookDto) {
        libraryService.updateBook(bookDto);
        return "redirect:/";
    }

//    @PostMapping("/updatebook")
//    public String updateBook()
}
