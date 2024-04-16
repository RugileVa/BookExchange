package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.Person;
import BookExchange.BookCrosser.persons.PersonsDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
    private ViewAdvertDTO convertToViewAdvertDTO(Advert advert, Person person){
        ViewAdvertDTO dto = new ViewAdvertDTO();
        dto.setDate(advert.getDate());
        dto.setTag(advert.getTag());
        dto.setTitle(advert.getTitle());
        dto.setAuthor(advert.getAuthor());
        dto.setGenre(advert.getGenre());
        dto.setCondition(advert.getCondition());
        dto.setPrice(advert.getPrice());
        dto.setAdvertImage(advert.getAdvertImage());

        PersonsDetailsDTO personDTO = new PersonsDetailsDTO();

        personDTO.setUsername(person.getUsername());
       // personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setEmail(person.getEmail());
        personDTO.setPicture(person.getPicture());

        dto.setPersonsDetailsDTO(dto.getPersonsDetailsDTO());
        return dto;
    }
    public List<Advert> getRecentAdverts(){
        return advertRepository.findRecentAdverts();
    }
    public List<ViewAdvertDTO> getRecentAdvertDTO(){
        List<Advert> recentAdverts = advertRepository.findRecentAdverts();
        return recentAdverts.stream()
                .map(this::convertToViewAdvertDTO)
                .collect(Collectors.toList());
    }

}
