package mk.ukim.finki.mk.lab.web;

import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;
import mk.ukim.finki.mk.lab.service.BookService;
import mk.ukim.finki.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;

    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping(path={"/edit-form/{id}", "/add-form"})
    public String editBook(@RequestParam(required = false) String error, @PathVariable(required = false) Long id, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "redirect:/books?error=BookNotFound";
        }
        List<BookStore> bookstores = bookStoreService.findAll();
        model.addAttribute("bookstores", bookstores);
        if (this.bookService.findBookById(id) != null && id!=null) {
            Book book = this.bookService.findBookById(id);
            model.addAttribute("book", book);
            return "add-book";
        }else{
            List<Book> books = this.bookService.listBooks();
            model.addAttribute("books", books);
            return "add-book";
        }
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam (name = "bookId", required = false) Long bookId,
                           @RequestParam Long bookStoreId) {
        this.bookService.createBook(isbn, title, genre, year, bookId, bookStoreId);
        return "redirect:/books";
    }

    @PostMapping()
    public String redirectToAuthorsPage(@RequestParam String bookIsbn){
        return "redirect:authors?isbn=" + bookIsbn;
    }
}
