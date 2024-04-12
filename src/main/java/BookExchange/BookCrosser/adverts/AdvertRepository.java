package BookExchange.BookCrosser.adverts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends
        JpaRepository<Advert, Long> {

}