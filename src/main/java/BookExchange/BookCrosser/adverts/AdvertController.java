package BookExchange.BookCrosser.adverts;

import BookExchange.BookCrosser.persons.Person;
import BookExchange.BookCrosser.persons.PersonsController;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/adverts/")
public class AdvertController {
    private final AdvertService advertService;
    public AdvertController(AdvertService advertService){
        this.advertService = advertService;
    }

     //displayAdverts function. This will listen for the page number input, and based on that will take the adverts from the list and display them
    @GetMapping("/")
    public ResponseEntity<List<DisplayAdvertDTO>> displayableAdverts(@RequestParam int pageNum,
                                                                     @RequestParam(required = false) String title,
                                                                     @RequestParam(required = false) String author,
                                                                     @RequestParam(required = false) String genre,
                                                                     @RequestParam(required = false) TAG_TYPE tag){
        int advertCount = 10;
        List<DisplayAdvertDTO> recentAdverts = advertService.getDisplayAdvertDTO(title,author,genre,tag);
        //offset is needed
        int startIndex = (pageNum * advertCount)-advertCount;
        int endIndex = Math.min(startIndex + advertCount, recentAdverts.size());
        if(startIndex >= recentAdverts.size()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<DisplayAdvertDTO> advertsToBeDisplayed = recentAdverts.subList(startIndex,endIndex);
        return ResponseEntity.ok(advertsToBeDisplayed);
    }

    @GetMapping("/view")
    public ResponseEntity<ViewAdvertDTO> seeAdvertDetails(@RequestParam Long id){
        Optional<Advert> optionalAdvert = advertService.findById(id);
        if(optionalAdvert.isEmpty()){
            //will return empty viewAdvertDTO for now
            return ResponseEntity.ok(new ViewAdvertDTO());
        }
        Advert advert = optionalAdvert.get();
        ViewAdvertDTO viewAdvertDTO = advertService.convertToViewAdvertDTO(advert);
        return ResponseEntity.ok(viewAdvertDTO);
    }
}
