package BookExchange.BookCrosser.persons;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonsDetailsDTO {

    private String username;
    private String phoneNumber;
    private String email;
    private byte[] picture;

}