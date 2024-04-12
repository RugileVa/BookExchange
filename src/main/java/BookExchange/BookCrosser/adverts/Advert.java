package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.histories.History;
import BookExchange.BookCrosser.persons.Person;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private TAG_TYPE tag;

    private String title;

    private String author;

    private String genre;

    private String condition;

    private BigDecimal price;

    private byte[] advertImage;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "advert")
    private List<History> historyEntries = new ArrayList<>();
    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", date=" + date +
                ", tag=" + tag +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", advertImage=" + Arrays.toString(advertImage) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) && Objects.equals(date, advert.date) && tag == advert.tag && Objects.equals(title, advert.title) && Objects.equals(author, advert.author) && Objects.equals(genre, advert.genre) && Objects.equals(condition, advert.condition) && Objects.equals(price, advert.price) && Arrays.equals(advertImage, advert.advertImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, date, tag, title, author, genre, condition, price);
        result = 31 * result + Arrays.hashCode(advertImage);
        return result;
    }
}
