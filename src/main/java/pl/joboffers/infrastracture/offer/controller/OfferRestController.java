package pl.joboffers.infrastracture.offer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseObjectDto;

import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping
    public ResponseEntity<List<OfferGetResponseObjectDto>> getAllOffers() {
        List<OfferGetResponseObjectDto> allOffers = offerFacade.getAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferGetResponseObjectDto> getOfferById(@PathVariable String id) {
        OfferGetResponseObjectDto offerById = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping
    public ResponseEntity<OfferPostResponseObjectDto> postOffer(@RequestBody OfferPostResponseObjectDto offer) {
        OfferPostResponseObjectDto offerGetResponseObjectDto = offerFacade.addManualJobOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(offerGetResponseObjectDto);
    }
}