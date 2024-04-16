package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.PersonsController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/adverts/")

public class AdvertController {
    private final AdvertService advertService;
    public AdvertController(AdvertService advertService){
        this.advertService = advertService;
    }

     //displayAdverts function. This will listen for the page number input, and based on that will take the adverts from the list and display them
    @GetMapping("/")
    public ResponseEntity<List<ViewAdvertDTO>> displayableAdverts(@RequestParam int pageNum){
        int advertCount = 10;
        List<ViewAdvertDTO> recentAdverts = advertService.getRecentAdvertDTO();
        //offset is needed
        int startIndex = (pageNum * advertCount)-advertCount;
        int endIndex = Math.min(startIndex + advertCount, recentAdverts.size());
        if(startIndex >= recentAdverts.size()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<ViewAdvertDTO> advertsToBeDisplayed = recentAdverts.subList(startIndex,endIndex);
        return ResponseEntity.ok(advertsToBeDisplayed);
    }

}
