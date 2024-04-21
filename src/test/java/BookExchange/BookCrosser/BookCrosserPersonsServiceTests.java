package BookExchange.BookCrosser;

import BookExchange.BookCrosser.persons.*;
import BookExchange.BookCrosser.persons.dto.*;
import BookExchange.BookCrosser.general.UnauthorizedAccessException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class BookCrosserPersonsServiceTests {

	@Mock
	private PersonRepository personRepository; // Mock repository for people

	@InjectMocks
	private PersonsService personsService;

	@BeforeEach
	public void setup() {
		//MockitoAnnotations.openMocks(this);
		personsService = new PersonsService(personRepository); // Ensure the service is initialized with the mocks
	}

	@Test
	public void registerPerson_newPerson_createsPerson() {
		// Arrange
		// Mock Jwt and JwtAuthenticationToken
		Jwt jwt = Mockito.mock(Jwt.class);
		JwtAuthenticationToken jwtAuthenticationToken = Mockito.mock(JwtAuthenticationToken.class);

		// Set expected JWT claim
		when(jwt.getClaim("email")).thenReturn("user@example.com");
		when(jwtAuthenticationToken.getName()).thenReturn("firebaseId");
		when(jwtAuthenticationToken.getPrincipal()).thenReturn(jwt);

		// Set security context
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(jwtAuthenticationToken);
		SecurityContextHolder.setContext(securityContext);

		// Rest of the test setup
		// Mock other needed components
		PersonRepository personRepository = Mockito.mock(PersonRepository.class);
		PersonsService personsService = new PersonsService(personRepository);

		SignUpDTO signUpDTO = new SignUpDTO();
		signUpDTO.setUsername("newUser");
		signUpDTO.setPhoneNumber("123456789");

		when(personRepository.existsByFirebaseId("firebaseId")).thenReturn(false);

		// Act
		Person createdPerson = personsService.registerPerson(signUpDTO);

		// Assert
		assertNotNull(createdPerson);
		assertEquals("newUser", createdPerson.getUsername());
		verify(personRepository, times(1)).save(any(Person.class));
	}

	@Test
	public void registerPerson_existingPerson_throwsUserAlreadyExistsException() {
		// Arrange
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		when(personRepository.existsByFirebaseId("firebaseId")).thenReturn(true);

		SignUpDTO signUpDTO = new SignUpDTO();
		signUpDTO.setUsername("existingUser");

		// Act & Assert
		assertThrows(UserAlreadyExistsException.class, () -> {
			personsService.registerPerson(signUpDTO);
		});
	}

	@Test
	public void getPersonDetails_existingPerson_returnsPersonDetails() {
		// Arrange
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		Person person = new Person();
		person.setFirebaseId("firebaseId");
		person.setUsername("testUser");
		person.setEmail("user@example.com");
		person.setPhoneNumber("123456789");

		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		// Act
		PersonsDetailsDTO personDetails = personsService.getPersonDetails();

		// Assert
		assertNotNull(personDetails);
		assertEquals("testUser", personDetails.getUsername());
		assertEquals("123456789", personDetails.getPhoneNumber());
		assertEquals("user@example.com", personDetails.getEmail());
	}

	@Test
	public void getPersonDetails_noPerson_throwsEntityNotFoundException() {
		// Arrange
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(EntityNotFoundException.class, () -> {
			personsService.getPersonDetails();
		});
	}

	@Test
	public void deletePerson_authenticatedPerson_deletesPerson() {
		// Arrange
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		Person person = new Person();
		person.setFirebaseId("firebaseId");

		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		// Act
		personsService.deletePerson();

		// Assert
		verify(personRepository, times(1)).delete(person);
	}

	@Test
	public void deletePerson_noAuthentication_throwsUnauthorizedAccessException() {
		// Arrange
		SecurityContextHolder.getContext().setAuthentication(null);

		// Act & Assert
		assertThrows(UnauthorizedAccessException.class, () -> {
			personsService.deletePerson();
		});
	}

}

