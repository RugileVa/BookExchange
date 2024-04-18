package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    public ViewAdvertDTO convertToViewAdvertDTO(Advert advert){
        ViewAdvertDTO dto = new ViewAdvertDTO();
        dto.setDate(advert.getDate());
        dto.setTag(advert.getTag());
        dto.setTitle(advert.getTitle());
        dto.setAuthor(advert.getAuthor());
        dto.setGenre(advert.getGenre());
        dto.setDescription(advert.getDescription());
        dto.setAdvertImage(advert.getAdvertImage());
        Person person = advert.getPerson();
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setPhoneNumber(person.getPhoneNumber());
        return dto;
    }
    public Optional<Advert> findById(Long id){
        return advertRepository.findById(id);
    }
    private DisplayAdvertDTO convertToDisplayAdvertDTO(Advert advert){
        DisplayAdvertDTO dto = new DisplayAdvertDTO();
        dto.setId(advert.getId());
        dto.setDate(advert.getDate());
        dto.setTag(advert.getTag());
        dto.setTitle(advert.getTitle());
        dto.setAuthor(advert.getAuthor());
        dto.setGenre(advert.getGenre());
        dto.setDescription(advert.getDescription());
        dto.setAdvertImage(advert.getAdvertImage());
        return dto;
    }
    public List<DisplayAdvertDTO> getDisplayAdvertDTO(String title, String author, String genre, TAG_TYPE tag){
        List<Advert> recentAdverts = advertRepository.findAdvertsBySearchCriteria(title,author,genre,tag);
        return recentAdverts.stream()
                .map(this::convertToDisplayAdvertDTO)
                .collect(Collectors.toList());
    }
    public List<Advert> findAdvertsBySearchCriteria(String title, String author, String genre, TAG_TYPE tag){
       return advertRepository.findAdvertsBySearchCriteria(title, author, genre, tag);

    }

}
