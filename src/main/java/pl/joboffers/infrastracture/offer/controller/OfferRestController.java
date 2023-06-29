package pl.joboffers.infrastracture.offer.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.joboffers.domain.offer.*;
import pl.joboffers.domain.offer.dto.*;

import java.util.*;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping
    public ResponseEntity<List<OfferResponseObjectDto>> getAllOffers() {
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        return ResponseEntity.ok(allOffers);
    }
}