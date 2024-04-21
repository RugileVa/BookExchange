package BookExchange.BookCrosser.general;

import BookExchange.BookCrosser.adverts.Advert;
import BookExchange.BookCrosser.adverts.TAG_TYPE;
import BookExchange.BookCrosser.adverts.AdvertRepository;
import BookExchange.BookCrosser.histories.History;
import BookExchange.BookCrosser.histories.HistoryRepository;
import BookExchange.BookCrosser.persons.Person;
import BookExchange.BookCrosser.persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;



@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AdvertRepository advertRepository;
    private final PersonRepository personRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public DatabaseSeeder(AdvertRepository advertRepository, PersonRepository personRepository, HistoryRepository historyRepository) {
        this.advertRepository = advertRepository;
        this.personRepository = personRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public void run(String... args) {
        // Seed persons
        Person person1 = new Person();
        person1.setUsername("BookReader3000");
        person1.setPhoneNumber("22213123");
        person1.setEmail("person1@example.com");

        // Set other properties for person1
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setUsername("ILoVeHarryPotter");
        person2.setPhoneNumber("2212312");
        person2.setEmail("person2@example.com");

        // Set other properties for person2
        personRepository.save(person2);

        // Seed adverts
        Advert advert1 = new Advert();
        advert1.setDate(new Date());
        advert1.setTag(TAG_TYPE.BUY);
        advert1.setTitle("Advert Title 1");
        advert1.setAuthor("Author 1");
        advert1.setGenre("Genre 1");
        advert1.setDescription("You will love this book!");
        advert1.setPerson(person1);
        // Set other properties for advert1
        advertRepository.save(advert1);

        Advert advert2 = new Advert();
        advert2.setDate(Calendar.getInstance().getTime());
        advert2.setTag(TAG_TYPE.BUY);
        advert2.setTitle("Advert Title 2");
        advert2.setAuthor("Author 2");
        advert2.setGenre("Genre 2");
        advert2.setDescription("A very nice book!");
        advert2.setPerson(person2);
        // Set other properties for advert2
        advertRepository.save(advert2);

        // Seed history entries
        History history1 = new History();
        history1.setDate(new Date());
        history1.setAdvert(advert1);
        history1.setPerson(person1);
        // Set other properties for history1
        historyRepository.save(history1);

        History history2 = new History();
        history2.setDate(new Date());
        history2.setAdvert(advert2);
        history2.setPerson(person2);
        // Set other properties for history2
        historyRepository.save(history2);
    }
}
