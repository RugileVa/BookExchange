package BookExchange.BookCrosser.adverts;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    public List<Advert> getRecentAdverts(){
        return advertRepository.findRecentAdverts();
    }

}
