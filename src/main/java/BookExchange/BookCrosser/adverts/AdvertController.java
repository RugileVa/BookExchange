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
    public ResponseEntity<ViewAdvertDTO> seeAdvertDetails(@RequestParam Long advertId){
        Optional<Advert> optionalAdvert = advertService.findAdvertById(advertId);
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
    @PostMapping("/update")
    public ResponseEntity<?> updateAdvert(@RequestBody AdvertDTO advertDTO){
        advertService.updateAdvert(advertDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertDTO);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAdvert(@RequestParam Long advertId){
        advertService.deleteAdvert(advertId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
