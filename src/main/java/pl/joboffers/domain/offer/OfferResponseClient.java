package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;

import java.util.List;

public interface OfferResponseClient {

    List<OfferGetResponseObjectDto> getAllOffers();
}