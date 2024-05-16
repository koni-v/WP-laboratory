package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Book addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);

    //lab2
    void deleteById(Long id);
    Book findBookById(Long id);
    Book createBook(String isbn , String title, String genre, Integer year, Long bookId, Long bookStoreId);
}
