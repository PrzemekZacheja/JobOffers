package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

public interface OfferResponseClient {

    List<OfferGetResponseDto> getAllOffers();
}