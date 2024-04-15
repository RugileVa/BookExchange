package BookExchange.BookCrosser.persons;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDTO {
    @NotNull
    private String username;

    @NotNull
    private String phoneNumber;
}
