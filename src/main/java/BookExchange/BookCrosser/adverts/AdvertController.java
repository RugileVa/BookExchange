package BookExchange.BookCrosser.adverts;


import org.springframework.http.HttpStatus;
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
    @GetMapping("/")
    public ResponseEntity<List<AdvertDTO>> displayableAdverts(@ModelAttribute FiltersDTO filtersDTO){
        List<AdvertDTO> advertDTOList = advertService.displayAdverts(filtersDTO);
        return ResponseEntity.ok(advertDTOList);
    }

    @GetMapping("/view")
    public ResponseEntity<ViewAdvertDTO> seeAdvertDetails(@RequestParam Long id){
        Optional<Advert> optionalAdvert = advertService.findAdvertById(id);
        if(optionalAdvert.isEmpty()){
            //will return empty viewAdvertDTO for now
            return ResponseEntity.ok(new ViewAdvertDTO());
        }
        Advert advert = optionalAdvert.get();
        ViewAdvertDTO viewAdvertDTO = advertService.convertToViewAdvertDTO(advert);
        return ResponseEntity.ok(viewAdvertDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createAdvert(@RequestBody AdvertDTO advertDTO){
        advertService.createAdvert(advertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(advertDTO);
    }
}
