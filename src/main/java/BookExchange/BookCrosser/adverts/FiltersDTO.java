package BookExchange.BookCrosser.adverts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FiltersDTO {
    private int pageNum;
    private String title;
    private String author;
    private String genre;
    private TAG_TYPE tag;
}
