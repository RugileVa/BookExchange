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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(email, person.email) && Objects.equals(password, person.password) && Objects.equals(phoneNumber, person.phoneNumber) && Arrays.equals(picture, person.picture) && Objects.equals(adverts, person.adverts) && Objects.equals(histories, person.histories);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, email, password, phoneNumber, adverts, histories);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
