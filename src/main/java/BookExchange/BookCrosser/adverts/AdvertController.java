package BookExchange.BookCrosser.adverts;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get adverts with filtering.")
    @GetMapping("/")
    public ResponseEntity<List<AdvertDTO>> displayableAdverts(@ModelAttribute FiltersDTO filtersDTO){
        List<AdvertDTO> advertDTOList = advertService.displayAdverts(filtersDTO);
        return ResponseEntity.ok(advertDTOList);
    }

    @Operation(summary = "Get advert details.")
    @GetMapping("/view")
    public ResponseEntity<ViewAdvertDTO> seeAdvertDetails(@RequestParam Long advertId){
        Advert advert = advertService.findAdvertById(advertId);  // This can throw EntityNotFoundException, handled by GlobalExceptionHandler
        ViewAdvertDTO viewAdvertDTO = advertService.convertToViewAdvertDTO(advert);
        return ResponseEntity.ok(viewAdvertDTO);
    }

    @Operation(summary = "Get currently authenticated user adverts.")
    @GetMapping("/profile")
    public ResponseEntity<List<AdvertDTO>> handlePersonAdverts(){
        List<AdvertDTO> userAdverts = advertService.personAdverts();
        return ResponseEntity.ok(userAdverts);
    }

    @Operation(summary = "Create an advert.")
    @PostMapping("/create")
    public ResponseEntity<?> handleCreateAdvert(@RequestBody AdvertDTO advertDTO){
        advertService.createAdvert(advertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(advertDTO);
    }

    @Operation(summary = "Update an advert.")
    @PostMapping("/update")
    public ResponseEntity<?> handleUpdateAdvert(@RequestBody AdvertDTO advertDTO){
        advertService.updateAdvert(advertDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(advertDTO);
    }
    @Operation(summary = "Delete a user advert.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> handleDeleteAdvert(@RequestParam Long advertId){
        advertService.deleteAdvert(advertId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
