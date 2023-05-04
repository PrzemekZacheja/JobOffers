package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

public class OfferResponseMapper {

    public static OfferResponseObject mapToOfferResponse(OfferResponseObjectDto offer) {
        return OfferResponseObject.builder()
                .linkToOffer(offer.linkToOffer())
                .nameOfCompany(offer.nameOfCompany())
                .nameOfPosition(offer.nameOfPosition())
                .salary(offer.salary())
                .build();
    }

    public static OfferResponseObjectDto mapToOfferResponseDto(OfferResponseObject offer) {
        return OfferResponseObjectDto.builder()
                .linkToOffer(offer.linkToOffer())
                .nameOfCompany(offer.nameOfCompany())
                .nameOfPosition(offer.nameOfPosition())
                .salary(offer.salary())
                .build();
    }
}