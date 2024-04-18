package BookExchange.BookCrosser.adverts;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class DisplayAdvertDTO {
    private Long id;
    private Date date;
    private TAG_TYPE tag;
    private String title;
    private String author;
    private String genre;
    private String description;
    private byte[] advertImage;
}
