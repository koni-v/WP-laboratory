package mk.ukim.finki.mk.lab.web;

import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.service.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/bookDetails")
public class BookDetailsController {
    private final BookService bookService;

    public BookDetailsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBookDetailsPage(@RequestParam(required = false) String error, @RequestParam String isbn, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "redirect:/books?error=BookNotFound";
        }

        Book book = bookService.findBookByIsbn(isbn);
        Long bookId = book.getId();
        if(bookId != null){
            model.addAttribute("isbn", isbn);
            model.addAttribute("book", book);
            model.addAttribute("authors", book.getAuthors());
            return "bookDetails";
        }
        return "redirect:/books?error=BookNotFound";
    }
}
