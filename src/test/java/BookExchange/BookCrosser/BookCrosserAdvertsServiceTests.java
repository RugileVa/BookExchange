package BookExchange.BookCrosser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import BookExchange.BookCrosser.adverts.Advert;
import BookExchange.BookCrosser.adverts.AdvertDTO;
import BookExchange.BookCrosser.adverts.AdvertRepository;
import BookExchange.BookCrosser.persons.PersonRepository;
import BookExchange.BookCrosser.adverts.AdvertService;
import BookExchange.BookCrosser.general.UnauthorizedAccessException;
import BookExchange.BookCrosser.persons.Person;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookCrosserAdvertsServiceTests {

	@Mock
	private AdvertRepository advertRepository;

	@Mock
	private PersonRepository personRepository; // Mock repository for people

	@InjectMocks
	private AdvertService advertService;

	@BeforeEach
	public void setup() {
		//MockitoAnnotations.openMocks(this);
		advertService = new AdvertService(advertRepository, personRepository); // Ensure the service is initialized with the mocks
	}

	@Test
	public void findAdvertById_existingId_returnsAdvert() {
		// Arrange
		Long advertId = 1L; // The ID to test
		Advert expectedAdvert = new Advert();
		expectedAdvert.setId(advertId); // Setting ID to expected advert

		// Mocking repository to return expected advert
		when(advertRepository.findById(advertId)).thenReturn(Optional.of(expectedAdvert));

		// Act
		Advert actualAdvert = advertService.findAdvertById(advertId); // Call the service method

		// Assert
		assertNotNull(actualAdvert, "The advert should not be null"); // Ensure the advert is returned
		assertEquals(expectedAdvert.getId(), actualAdvert.getId(), "Advert ID should match"); // Validate ID matches
	}

	@Test
	public void findAdvertById_nonExistingId_throwsEntityNotFoundException() {
		// Arrange
		Long nonExistingId = 99L; // Non-existing ID
		when(advertRepository.findById(nonExistingId)).thenReturn(Optional.empty()); // Mock repository to return empty

		// Act & Assert
		assertThrows(EntityNotFoundException.class, () -> {
			advertService.findAdvertById(nonExistingId); // This should throw an exception
		}, "Expected EntityNotFoundException when advert is not found");
	}

	@Test
	public void createAdvert_authenticatedPerson_createsAdvert() {
		// Arrange
		UserDetails userDetails = User.withUsername("user1").password("password").roles("USER").build();
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		Person person = new Person();
		person.setFirebaseId("firebaseId");
		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		AdvertDTO advertDTO = new AdvertDTO();
		advertDTO.setTitle("Book Title");
		advertDTO.setAuthor("Book Author");

		// Act
		advertService.createAdvert(advertDTO);

		// Assert
		verify(advertRepository, times(1)).save(any(Advert.class));
	}

	@Test
	public void createAdvert_noAuthentication_throwsUnauthorizedAccessException() {
		// Arrange
		// Ensure there's no authentication in the SecurityContext
		SecurityContextHolder.getContext().setAuthentication(null);

		// Create an AdvertDTO with some basic data
		AdvertDTO advertDTO = new AdvertDTO();
		advertDTO.setTitle("Book Title");

		// Act & Assert
		// The expected behavior when trying to create an advert without authentication
		assertThrows(UnauthorizedAccessException.class, () -> {
			advertService.createAdvert(advertDTO); // Attempt to create an advert
		});
	}

	@Test
	public void updateAdvert_existingAdvert_updatesAdvert() {
		// Arrange
		// Set up authentication and SecurityContext with a mock user
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		Person person = new Person();
		person.setFirebaseId("firebaseId");
		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		Long advertId = 1L;
		Advert existingAdvert = new Advert();
		existingAdvert.setId(advertId);

		when(advertRepository.findById(advertId)).thenReturn(Optional.of(existingAdvert));

		AdvertDTO advertDTO = new AdvertDTO();
		advertDTO.setId(advertId);
		advertDTO.setTitle("Updated Title");

		// Act
		advertService.updateAdvert(advertDTO);

		// Assert
		verify(advertRepository, times(1)).save(existingAdvert);
		assertEquals("Updated Title", existingAdvert.getTitle());
	}


	@Test
	public void updateAdvert_noAuthentication_throwsUnauthorizedAccessException() {
		// Arrange
		SecurityContextHolder.getContext().setAuthentication(null); // No authentication
		AdvertDTO updatedAdvertDTO = new AdvertDTO(); // Data to update
		updatedAdvertDTO.setId(1L); // ID for the advert

		// Act & Assert
		assertThrows(UnauthorizedAccessException.class, () -> {
			advertService.updateAdvert(updatedAdvertDTO);
		}, "Expected UnauthorizedAccessException when not authenticated");
	}

	@Test
	public void deleteAdvert_existingAdvert_deletesAdvert() {
		// Arrange
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId"); // Firebase ID in CamelCase
		SecurityContextHolder.setContext(securityContext);

		Person person = new Person();
		person.setFirebaseId("firebaseId");
		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		Long advertId = 1L;
		Advert existingAdvert = new Advert();
		existingAdvert.setId(advertId);
		when(advertRepository.findById(advertId)).thenReturn(Optional.of(existingAdvert)); // Mock the repository to find the advert

		// Act
		advertService.deleteAdvert(advertId); // Attempt to delete the advert

		// Assert
		verify(advertRepository, times(1)).delete(existingAdvert); // Verify the advert was deleted
	}

	@Test
	public void deleteAdvert_nonExistingAdvert_throwsEntityNotFoundException() {
		// Arrange
		// Mock authentication and security context to simulate an authenticated user
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId"); // Example user ID
		SecurityContextHolder.setContext(securityContext);

		// Simulate a Person corresponding to the authenticated user
		Person person = new Person();
		person.setFirebaseId("firebaseId");
		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		// Set up a non-existing advert ID and mock the repository to return an empty optional
		Long nonExistingId = 99L;
		when(advertRepository.findById(nonExistingId)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(EntityNotFoundException.class, () -> {
			advertService.deleteAdvert(nonExistingId); // Should throw an exception
		}, "Expected EntityNotFoundException when trying to delete a non-existing advert");
	}

	@Test
	public void personAdverts_authenticatedUser_returnsAdverts() {
		// Arrange
		// Set up authentication and security context with a mock user
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("firebaseId");
		SecurityContextHolder.setContext(securityContext);

		// Create a person and simulate the repository finding this person
		Person person = new Person();
		person.setFirebaseId("firebaseId");
		when(personRepository.findByFirebaseId("firebaseId")).thenReturn(Optional.of(person));

		// Create a list with one advert belonging to this person
		List<Advert> personAdverts = new ArrayList<>();
		Advert advert = new Advert();
		advert.setTitle("Person's Advert");
		personAdverts.add(advert);

		when(advertRepository.findAdvertsByPerson(person)).thenReturn(personAdverts);

		// Act
		List<AdvertDTO> result = advertService.personAdverts(); // Retrieve adverts for authenticated user

		// Assert
		assertNotNull(result, "The result should not be null");
		assertEquals(1, result.size(), "There should be one advert");
		assertEquals("Person's Advert", result.get(0).getTitle(), "The advert title should match");
	}
	@Test
	public void personAdverts_noAuthentication_returnsEmptyList() {
		// Arrange
		// Ensure there's no authentication in the SecurityContext
		SecurityContextHolder.getContext().setAuthentication(null);

		// Act
		List<AdvertDTO> result = advertService.personAdverts(); // Retrieve adverts with no authentication

		// Assert
		assertTrue(result.isEmpty(), "The result should be empty when there's no authentication");
	}

}