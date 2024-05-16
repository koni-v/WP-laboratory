package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
