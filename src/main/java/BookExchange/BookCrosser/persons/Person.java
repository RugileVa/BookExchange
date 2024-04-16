package BookExchange.BookCrosser.persons;

import BookExchange.BookCrosser.adverts.Advert;
import BookExchange.BookCrosser.histories.History;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firebaseId;

    private String username;

    private String phoneNumber;

    private String email;

    private byte[] picture;

    @OneToMany(mappedBy = "person")
    private List<Advert> adverts = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<History> histories = new ArrayList<>();

}
