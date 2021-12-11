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
        model.addAttribute("authorsList", libraryService.findAllAuthors());
        return "book-list";
    }

    @GetMapping("/getForm/{id}")
    public String getForm(@PathVariable (name = "id") String id, Model model) {
        model.addAttribute("bookDto", libraryService.updateBookForm(id));
        model.addAttribute("authorsList", libraryService.findAllAuthors());
        return "add-update-book";
    }

    @GetMapping("/getForm")
    public String getForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("authorsList", libraryService.findAllAuthors());
        return "add-update-book";
    }

    @PostMapping("/updatebook")
    public String updateBook(BookDto bookDto) {
        libraryService.addBook(bookDto);
        return "redirect:/";
    }
}
