package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.PersonsController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/adverts/")

public class AdvertController {
    private final AdvertService advertService;
    public AdvertController(AdvertService advertService){
        this.advertService = advertService;
    }

    // displayAdverts function. This will listen for the page number input, and based on that will take the adverts from the list and display them
    @PostMapping("/")
    public ResponseEntity<List<Advert>> displayableAdverts(@RequestParam int pageNum){
        List<Advert> recentAdverts = advertService.getRecentAdverts();
        return ResponseEntity.ok(recentAdverts);
    }
}
