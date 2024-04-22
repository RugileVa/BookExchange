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
        person1.setFirebaseId("JDkhDVCo2rdJCAtyZFTg6GEZ36p2");
        person1.setUsername("BookReader3000");
        person1.setPhoneNumber("22213123");
        person1.setEmail("test2@test.com");

        // Set other properties for person1
        personRepository.save(person1);

        Person person2 = new Person();
        person2.setFirebaseId("CzNRj48CeaYlgsiszgTqFlVeGXF2");
        person2.setUsername("ILoVeHarryPotter");
        person2.setPhoneNumber("2212312");
        person2.setEmail("test1@test.com");

        // Set other properties for person2
        personRepository.save(person2);

        Person person3 = new Person();
        person3.setFirebaseId("FA1shqLMV2bN5vtdcGbJiW1CLdf1");
        person3.setUsername("CoolLibrarian89");
        person3.setPhoneNumber("22321232");
        person3.setEmail("test@test.com");

        // Set other properties for person3
        personRepository.save(person3);

        // Seed adverts
        Advert advert1 = new Advert();
        advert1.setDate(new Date());
        advert1.setTag(TAG_TYPE.BUY);
        advert1.setTitle("Whispers in the Woods");
        advert1.setAuthor("Emma Blackwood");
        advert1.setGenre("Thriller");
        advert1.setDescription("I really love this book, but cannot find it. I'm willing to pay to up-to 20 eur.");
        advert1.setPerson(person1);
        // Set other properties for advert1
        advertRepository.save(advert1);

        Advert advert2 = new Advert();
        advert2.setDate(new Date());
        advert2.setTag(TAG_TYPE.BUY);
        advert2.setTitle("Echoes of Eternity");
        advert2.setAuthor("Alexander Cross");
        advert2.setGenre("Fantasy");
        advert2.setDescription("A rare fantasy book that i want to buy for my collection. Contact me!");
        advert2.setPerson(person1);
        // Set other properties for advert2
        advertRepository.save(advert2);

        Advert advert3 = new Advert();
        advert3.setDate(new Date());
        advert3.setTag(TAG_TYPE.EXCHANGE);
        advert3.setTitle("The Last Voyage of the Celestial");
        advert3.setAuthor("Marina Grey");
        advert3.setGenre("Science Fiction");
        advert3.setDescription("Just finnished reading this book. I'm willing to exchange it to something similar");
        advert3.setPerson(person1);
        advertRepository.save(advert3);

        Advert advert4 = new Advert();
        advert4.setDate(new Date());
        advert4.setTag(TAG_TYPE.EXCHANGE);
        advert4.setTitle("Silent Symphony");
        advert4.setAuthor("Harper Finch");
        advert4.setGenre("Romance");
        advert4.setDescription("Will exchange this for anything. Really didn't like this one");
        advert4.setPerson(person1);
        advertRepository.save(advert4);

        Advert advert5 = new Advert();
        advert5.setDate(new Date());
        advert5.setTag(TAG_TYPE.FOR_FREE);
        advert5.setTitle("Shadows of the Forgotten");
        advert5.setAuthor("Liam Rivers");
        advert5.setGenre("Historical Fiction");
        advert5.setDescription("Cleaning my book shelf and can give this book away for free #savetrees");
        advert5.setPerson(person1);
        advertRepository.save(advert5);

        Advert advert6 = new Advert();
        advert6.setDate(new Date());
        advert6.setTag(TAG_TYPE.FOR_FREE);
        advert6.setTitle("The Oracle's Prophecy");
        advert6.setAuthor("Maya Stone");
        advert6.setGenre("Adventure");
        advert6.setDescription("I have multiple copies of this book. Can give away some of them if interested.");
        advert6.setPerson(person1);
        advertRepository.save(advert6);

        Advert advert7 = new Advert();
        advert7.setDate(new Date());
        advert7.setTag(TAG_TYPE.FOR_FREE);
        advert7.setTitle("The Midnight Library");
        advert7.setAuthor("Lucy Graham");
        advert7.setGenre("Fiction");
        advert7.setDescription("Can't stop reading this book over and over again. Please take it away from me.");
        advert7.setPerson(person2);
        advertRepository.save(advert7);

        Advert advert8 = new Advert();
        advert8.setDate(new Date());
        advert8.setTag(TAG_TYPE.FOR_FREE);
        advert8.setTitle("City of Whispers");
        advert8.setAuthor("Samuel Reyes");
        advert8.setGenre("Urban Fantasy");
        advert8.setDescription("Fishing for good karma. Contact me so i can send this book");
        advert8.setPerson(person2);
        advertRepository.save(advert8);

        Advert advert9 = new Advert();
        advert9.setDate(new Date());
        advert9.setTag(TAG_TYPE.SELL);
        advert9.setTitle("Echoes of the Deep");
        advert9.setAuthor("Isabella Cruz");
        advert9.setGenre("Mystery");
        advert9.setDescription("An interesting book for history geeks, we can talk about the price");
        advert9.setPerson(person2);
        advertRepository.save(advert9);

        Advert advert10 = new Advert();
        advert10.setDate(new Date());
        advert10.setTag(TAG_TYPE.SELL);
        advert10.setTitle("The Alchemy of Fate");
        advert10.setAuthor("Oliver Knight");
        advert10.setGenre("Fantasy");
        advert10.setDescription("Didn't like it. 5 eur. Maybe even 1, if you bring french fries");
        advert10.setPerson(person2);
        advertRepository.save(advert10);

        Advert advert11 = new Advert();
        advert11.setDate(new Date());
        advert11.setTag(TAG_TYPE.BUY);
        advert11.setTitle("The Forgotten Kingdom");
        advert11.setAuthor("Elena Rodriguez");
        advert11.setGenre("Historical Fiction");
        advert11.setDescription("Will buy this book, beca. 10 eur max price");
        advert11.setPerson(person2);
        advertRepository.save(advert11);

        Advert advert12 = new Advert();
        advert12.setDate(new Date());
        advert12.setTag(TAG_TYPE.BUY);
        advert12.setTitle("The Clockmaker's Daughter");
        advert12.setAuthor("Benjamin Hart");
        advert12.setGenre("Historical Mystery");
        advert12.setDescription("Will buy. 15 eur. Please, I really need it !");
        advert12.setPerson(person2);
        advertRepository.save(advert12);

        Advert advert13 = new Advert();
        advert13.setDate(new Date());
        advert13.setTag(TAG_TYPE.EXCHANGE);
        advert13.setTitle("Starlight Serenade");
        advert13.setAuthor("Sophia Adams");
        advert13.setGenre("Romance");
        advert13.setDescription("Exchange for any children books");
        advert13.setPerson(person2);
        advertRepository.save(advert13);

        Advert advert14 = new Advert();
        advert14.setDate(new Date());
        advert14.setTag(TAG_TYPE.EXCHANGE);
        advert14.setTitle("The Art of Deception");
        advert14.setAuthor("Nathan Clarke");
        advert14.setGenre("Thriller");
        advert14.setDescription("Will exchange for any Harry Potter book");
        advert14.setPerson(person3);
        advertRepository.save(advert14);

        Advert advert15 = new Advert();
        advert15.setDate(new Date());
        advert15.setTag(TAG_TYPE.FOR_FREE);
        advert15.setTitle("Whispers of the Ancients");
        advert15.setAuthor("Emily Chang");
        advert15.setGenre("Adventure");
        advert15.setDescription("Don't like it. Will give away for free");
        advert15.setPerson(person3);
        advertRepository.save(advert15);

        Advert advert16 = new Advert();
        advert16.setDate(new Date());
        advert16.setTag(TAG_TYPE.BUY);
        advert16.setTitle("The Glass Garden");
        advert16.setAuthor("Jonathan Hayes");
        advert16.setGenre("Fiction");
        advert16.setDescription("Need this book for my collection");
        advert16.setPerson(person3);
        advertRepository.save(advert16);

        Advert advert17 = new Advert();
        advert17.setDate(new Date());
        advert17.setTag(TAG_TYPE.EXCHANGE);
        advert17.setTitle("The Songbird's Lament");
        advert17.setAuthor("Clara Evans");
        advert17.setGenre("Romance");
        advert17.setDescription("Will exchange for anny programming book");
        advert17.setPerson(person3);
        advertRepository.save(advert17);

        Advert advert18 = new Advert();
        advert18.setDate(new Date());
        advert18.setTag(TAG_TYPE.FOR_FREE);
        advert18.setTitle("The Shadowed Realm");
        advert18.setAuthor("Gabriel Knight");
        advert18.setGenre("Adventure");
        advert18.setDescription("Contact me if interested in this book");
        advert18.setPerson(person3);
        advertRepository.save(advert18);

        Advert advert19 = new Advert();
        advert19.setDate(new Date());
        advert19.setTag(TAG_TYPE.EXCHANGE);
        advert19.setTitle("The Forgotten City");
        advert19.setAuthor("Rachel Bennett");
        advert19.setGenre("Thriller");
        advert19.setDescription("Will exchange for any java book");
        advert19.setPerson(person3);
        advertRepository.save(advert19);

        Advert advert20 = new Advert();
        advert20.setDate(new Date());
        advert20.setTag(TAG_TYPE.SELL);
        advert20.setTitle("The Midnight Circus");
        advert20.setAuthor("Daniel Hartman");
        advert20.setGenre("Magical Realism");
        advert20.setDescription("Interesting book that will not let you sleep at night. 15 eur.");
        advert20.setPerson(person3);
        advertRepository.save(advert20);

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
