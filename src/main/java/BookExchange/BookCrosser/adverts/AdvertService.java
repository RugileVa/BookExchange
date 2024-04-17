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
    private ViewAdvertDTO convertToViewAdvertDTO(Advert advert){
        ViewAdvertDTO dto = new ViewAdvertDTO();
        dto.setDate(advert.getDate());
        dto.setTag(advert.getTag());
        dto.setTitle(advert.getTitle());
        dto.setAuthor(advert.getAuthor());
        dto.setGenre(advert.getGenre());
        dto.setAdvertImage(advert.getAdvertImage());

        PersonsDetailsDTO personDTO = new PersonsDetailsDTO();

        personDTO.setUsername(personDTO.getUsername());
        personDTO.setPhoneNumber(personDTO.getPhoneNumber());
        personDTO.setEmail(personDTO.getEmail());
        personDTO.setPicture(personDTO.getPicture());

        dto.setPersonsDetailsDTO(dto.getPersonsDetailsDTO());
        return dto;
    }
    public List<ViewAdvertDTO> getViewAdvertDTO(){
        List<Advert> recentAdverts = advertRepository.findRecentAdverts();
        return recentAdverts.stream()
                .map(this::convertToViewAdvertDTO)
                .collect(Collectors.toList());
    }
    private DisplayAdvertDTO convertToDisplayAdvertDTO(Advert advert){
        DisplayAdvertDTO dto = new DisplayAdvertDTO();
        dto.setDate(advert.getDate());
        dto.setTag(advert.getTag());
        dto.setTitle(advert.getTitle());
        dto.setAuthor(advert.getAuthor());
        dto.setGenre(advert.getGenre());
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
    public List<Advert> getRecentAdverts(){
        return advertRepository.findRecentAdverts();
    }
}
