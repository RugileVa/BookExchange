package BookExchange.BookCrosser.persons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p WHERE p.firebaseId = :firebaseId")
    boolean existsByFirebaseId(String firebaseId);
    @Query("SELECT p FROM Person p WHERE p.firebaseId = :firebaseId")
    Optional<Person> findByFirebaseId(String firebaseId);
}