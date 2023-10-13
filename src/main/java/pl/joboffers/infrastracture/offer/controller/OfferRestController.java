package pl.joboffers.infrastracture.offer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostRequestDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping
    public ResponseEntity<List<OfferGetResponseDto>> getAllOffers() {
        List<OfferGetResponseDto> allOffers = offerFacade.getAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferGetResponseDto> getOfferById(@PathVariable String id) {
        OfferGetResponseDto offerById = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping
    public ResponseEntity<OfferPostResponseDto> postOffer(@RequestBody @Valid OfferPostRequestDto offer) {
        OfferPostResponseDto offerGetResponseObjectDto = offerFacade.addManualJobOfferByObject(offer);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(offerGetResponseObjectDto);
    }
}