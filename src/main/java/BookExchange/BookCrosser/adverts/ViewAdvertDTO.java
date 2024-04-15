package BookExchange.BookCrosser.adverts;

import java.math.BigDecimal;
import java.util.Date;

import BookExchange.BookCrosser.persons.PersonsDetailsDTO;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ViewAdvertDTO {
    private Date date;
    private TAG_TYPE tag;
    private String title;

    private String author;

    private String genre;

    private String condition;

    private BigDecimal price;

    private byte[] advertImage;

}
