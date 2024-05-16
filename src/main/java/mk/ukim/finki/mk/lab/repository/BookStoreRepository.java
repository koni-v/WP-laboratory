package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
    BookStore findBookStoreById(Long id);
}
