package BookExchange.BookCrosser.persons;

import BookExchange.BookCrosser.adverts.Advert;
import BookExchange.BookCrosser.histories.History;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String phoneNumber;

    private byte[] picture;

    @OneToMany(mappedBy = "person")
    private List<Advert> adverts = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<History> histories = new ArrayList<>();
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

}
