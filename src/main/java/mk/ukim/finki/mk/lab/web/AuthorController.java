package mk.ukim.finki.mk.lab.web;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.service.AuthorService;
import mk.ukim.finki.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAuthorPage(@RequestParam(required = false) String error,
                                @RequestParam String isbn,
                                Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Author> authors = authorService.listAuthors();
        List<Book> books = bookService.listBooks();
        model.addAttribute("authors", authors);
        model.addAttribute("books", books);
        model.addAttribute("isbn", isbn);
        return "authorList";
    }

    @PostMapping("/add-author")
    public String addAuthorsToBook(@RequestParam String isbn,
                                   @RequestParam Long authorId){
        Book book = bookService.findBookByIsbn(isbn);
        if(book != null){
            bookService.addAuthorToBook(authorId, isbn);
            return "redirect:/bookDetails?isbn=" + isbn;
        }else{
            return "redirect:/error-page";
        }
    }
}
