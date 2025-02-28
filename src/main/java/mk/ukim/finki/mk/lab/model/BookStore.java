package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="bookstores")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;

    public BookStore( String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public BookStore() {

    }
}
