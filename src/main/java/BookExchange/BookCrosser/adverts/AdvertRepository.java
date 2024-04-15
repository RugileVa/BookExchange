package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    // Will return the List of adverts that are ordered by creation date starting from the most recent
    @Query("SELECT a FROM Advert a ORDER BY a.date DESC")
    List<Advert> findRecentAdverts();
}