package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

public class OfferResponseMapper {
    public static OfferResponseObject mapToOfferResponse(OfferResponseObjectDto offer) {
        return OfferResponseObject.builder()
                .nameOfJob(offer.nameOfJob())
                .description(offer.description())
                .salary(offer.salary())
                .build();
    }
}