package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

public interface OfferResponseClient {

    List<OfferResponseObjectDto> getAllOffers();
}