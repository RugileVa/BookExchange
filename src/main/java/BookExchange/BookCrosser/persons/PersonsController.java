package BookExchange.BookCrosser.persons;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons/")
public class PersonsController {

    private final PersonsService personsService;

    public PersonsController(PersonsService personsService) {
        this.personsService = personsService;
    }

    @Operation(summary = "Register person", description = "Provide username and phone number. Email and firefox id is accessed from access token.")
    @PostMapping("/register")
    public ResponseEntity<?> handleRegisterRequest(@RequestBody @Valid SignUpDTO data) {
        Person registeredPerson = personsService.registerPerson(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredPerson);
    }
    @Operation(summary = "Delete currently authenticated person from local storage.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> handleDeleteRequest() {
        personsService.deletePerson();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get currently authenticated person details.")
    @GetMapping("/details")
    public ResponseEntity<?> handleGetDetailsRequest() {
        PersonsDetailsDTO detailsDTO = personsService.getPersonDetails();
        return ResponseEntity.ok(detailsDTO);
    }

}
