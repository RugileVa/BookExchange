package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.net.UnknownServiceException;
import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    // Will return List of adverts by search criteria and order by creation date
    @Query("SELECT a FROM Advert a " +
            "WHERE (COALESCE(:title, '') = '' OR a.title ILIKE CONCAT('%', :title, '%')) " +
            "AND (COALESCE(:author, '') = '' OR a.author ILIKE CONCAT('%', :author, '%')) " +
            "AND (COALESCE(:genre, '') = '' OR a.genre ILIKE CONCAT('%', :genre, '%')) " +
            "AND (:tag IS NULL OR a.tag = :tag) " +
            "ORDER BY a.date DESC")
    List<Advert> findAdvertsBySearchCriteria(
            @Param("title") String title,
            @Param("author") String author,
            @Param("genre") String genre,
            @Param("tag") TAG_TYPE tag
    );
}