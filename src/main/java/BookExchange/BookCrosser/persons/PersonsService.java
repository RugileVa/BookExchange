package BookExchange.BookCrosser.persons;

import BookExchange.BookCrosser.general.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import  org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@Service
public class PersonsService {

    private final PersonRepository personRepository;
    public PersonsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person registerPerson(SignUpDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firebaseId = authentication.getName();

        if (personRepository.existsByFirebaseId(firebaseId)) {
            throw new UserAlreadyExistsException("User with Firebase ID already exists");
        }

        String email = getEmailFromJwt();

        Person person = new Person();
        person.setFirebaseId(firebaseId);
        person.setEmail(email);
        person.setUsername(data.getUsername());
        person.setPhoneNumber(data.getPhoneNumber());
        personRepository.save(person);

        return person;
    }

    public PersonsDetailsDTO getPersonDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String firebaseId = authentication.getName();
        Person person = personRepository.findByFirebaseId(firebaseId)
                .orElseThrow(() -> new EntityNotFoundException("Person details not found"));

        return mapPersonToDTO(person);
    }

    private PersonsDetailsDTO mapPersonToDTO(Person person) {
        PersonsDetailsDTO dto = new PersonsDetailsDTO();
        dto.setUsername(person.getUsername());
        dto.setPhoneNumber(person.getPhoneNumber());
        dto.setEmail(person.getEmail());
        dto.setPicture(person.getPicture());
        return dto;
    }

    public void deletePerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String firebaseId = authentication.getName();
            Optional<Person> optionalPerson = personRepository.findByFirebaseId(firebaseId);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                personRepository.delete(person);
            } else {
                throw new EntityNotFoundException("User not found");
            }
        } else {
            throw new UnauthorizedAccessException("User not authenticated");
        }
    }

    private String getEmailFromJwt() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      String email = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).claims.get("email");

        if (authentication instanceof JwtAuthenticationToken) {
            Object principal = ((JwtAuthenticationToken) authentication).getPrincipal();

            // Check if principal is a JWT
            if (principal instanceof Jwt) {
                Jwt jwt = (Jwt) principal;

                // Access claims from the JWT
                return jwt.getClaim("email");
            }
        }

        // If authentication is not JwtAuthenticationToken, return null
        return null;
    }

}



//    public void registerPerson(SignUpDTO data) {
//        Person person = new Person();
//        String email = "";
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////      String email = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials()).claims.get("email");
//
//        if (authentication instanceof JwtAuthenticationToken) {
//            Object principal = ((JwtAuthenticationToken) authentication).getPrincipal();
//
//            // Check if principal is a JWT
//            if (principal instanceof Jwt) {
//                Jwt jwt = (Jwt) principal;
//
//                // Access claims from the JWT
//                email = jwt.getClaim("email");
//            }
//        }
//
//        if (authentication != null) {
//            person.setFirebaseId(authentication.getName());
//            person.setEmail(email);
//            person.setUsername(data.getUsername());
//            personRepository.save(person);
//        }
//    }