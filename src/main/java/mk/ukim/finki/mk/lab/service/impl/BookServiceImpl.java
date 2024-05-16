package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;
import mk.ukim.finki.mk.lab.repository.AuthorRepository;
import mk.ukim.finki.mk.lab.repository.BookRepository;
import mk.ukim.finki.mk.lab.repository.BookStoreRepository;
import mk.ukim.finki.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book addAuthorToBook(Long authorId, String isbn) {
        Optional <Author> author = authorRepository.findById(authorId);
        Book book = bookRepository.findByIsbn(isbn);

        if(book != null && author.isPresent()){
            Author author1 = author.get();
            if (!book.getAuthors().contains(author1)) {
                book.getAuthors().add(author1);
                return bookRepository.save(book);
            }
        }
        return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    // lab 2
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book createBook(String isbn, String title, String genre, Integer year, Long bookId, Long bookStoreId){
        Book book= bookRepository.findBookById(bookId);

        if(book != null) {
            bookRepository.delete(book);
        }

        BookStore bookStore = bookStoreRepository.findBookStoreById(bookStoreId);
        book = new Book(isbn, title, genre, year, new ArrayList<>(), bookStore);
        return bookRepository.save(book);
    }
}
