package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String genre;

    private int year;

    @ManyToMany
    @JoinTable(name = "authors_of_books")
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "book_store_id")
    private BookStore bookStore;


    public Book(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore ) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        this.bookStore = bookStore;
    }

    public Book() {

    }
}
