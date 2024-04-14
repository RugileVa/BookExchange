package BookExchange.BookCrosser.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PersonsService {

    private final PersonRepository personRepository;
    public PersonsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void registerPerson() {
        Person person = new Person();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            person.setUsername(authentication.getName());
            personRepository.save(person);
        }
    }

}
