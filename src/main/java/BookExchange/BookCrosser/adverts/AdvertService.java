package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.general.UnauthorizedAccessException;
import BookExchange.BookCrosser.persons.Person;
import BookExchange.BookCrosser.persons.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final PersonRepository personRepository;

    public AdvertService(AdvertRepository advertRepository, PersonRepository personRepository) {
        this.advertRepository = advertRepository;
        this.personRepository = personRepository;
    }
    public Optional<Advert> findAdvertById(Long id){
        return advertRepository.findById(id);
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
    private AdvertDTO convertToAdvertDTO(Advert advert){
        AdvertDTO dto = new AdvertDTO();
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
    public List<AdvertDTO> displayAdverts(FiltersDTO filtersDTO){
        String title = filtersDTO.getTitle().isEmpty() ? null : filtersDTO.getTitle();
        String author = filtersDTO.getAuthor().isEmpty() ? null : filtersDTO.getAuthor();
        String genre = filtersDTO.getGenre().isEmpty() ? null : filtersDTO.getGenre();
        TAG_TYPE tag = (filtersDTO.getTag() != null ? filtersDTO.getTag() : null);
        int pageNum = filtersDTO.getPageNum();
        List<Advert> recentAdverts = advertRepository.findAdvertsBySearchCriteria(title,author,genre,tag);
        List<AdvertDTO>  displayAdvertDTOList = recentAdverts.stream()
                .map(this::convertToAdvertDTO)
                .toList();
        // number 10 stands for adverts per page. It is a fixed value for now
        int startIndex = (pageNum * 10) - 10;
        int endIndex = Math.min(startIndex + 10, displayAdvertDTOList.size());
        if(startIndex >= recentAdverts.size()){
            return Collections.emptyList();
        }
        return displayAdvertDTOList.subList(startIndex,endIndex);
    }
    public void createAdvert(AdvertDTO advertDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String firebaseId = authentication.getName();
            Optional<Person> optionalPerson = personRepository.findByFirebaseId(firebaseId);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                Advert advert = new Advert();
                advert.setDate(new Date());
                advert.setTitle(advertDTO.getTitle());
                advert.setAuthor(advertDTO.getAuthor());
                advert.setGenre(advertDTO.getGenre());
                advert.setDescription(advertDTO.getDescription());
                advert.setAdvertImage(advertDTO.getAdvertImage());
                advert.setPerson(person);
                advertRepository.save(advert);
            } else {
                throw new EntityNotFoundException("User not found");
            }
        } else {
            throw new UnauthorizedAccessException("User not authenticated");
        }
    }
    public void updateAdvert(AdvertDTO advertDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            Long advertId = advertDTO.getId();
            Optional<Advert> optionalAdvert = advertRepository.findById(advertId);
            if(optionalAdvert.isPresent()){
                Advert advert = optionalAdvert.get();
                advert.setId(advertDTO.getId());
                advert.setTitle(advertDTO.getTitle());
                advert.setAuthor(advertDTO.getAuthor());
                advert.setGenre(advertDTO.getGenre());
                advert.setDate(new Date());
                advert.setDescription(advertDTO.getDescription());
                advert.setTag(advertDTO.getTag());
                advert.setAdvertImage(advertDTO.getAdvertImage());
                advertRepository.save(advert);
            }
            else throw new EntityNotFoundException("Advert not found");
        }
        else throw new UnauthorizedAccessException("User not authenticated");
    }
    public void deleteAdvert(Long advertId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String firebaseId = authentication.getName();
            Optional<Person> optionalPerson = personRepository.findByFirebaseId(firebaseId);
            if(optionalPerson.isPresent()){
                Long currentUserId = optionalPerson.get().getId();
                Optional<Advert> optionalAdvertToDelete = advertRepository.findById(advertId);
                if(optionalAdvertToDelete.isPresent()){
                    Advert advertToDelete = optionalAdvertToDelete.get();
                    advertRepository.delete(advertToDelete);
                }
                else throw new EntityNotFoundException("Advert not found");
            }
            else throw new EntityNotFoundException("User not found");
        }
        else throw new UnauthorizedAccessException("User not authenticated");
    }
}
