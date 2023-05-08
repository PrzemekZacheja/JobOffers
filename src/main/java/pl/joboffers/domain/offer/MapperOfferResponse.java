package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

public class MapperOfferResponse {

    public static OfferResponseObject mapToOfferResponse(OfferResponseObjectDto offer) {
        return OfferResponseObject.builder()
                .id(offer.id())
                .linkToOffer(offer.linkToOffer())
                .nameOfCompany(offer.nameOfCompany())
                .nameOfPosition(offer.nameOfPosition())
                .salary(offer.salary())
                .build();
    }

    public static OfferResponseObjectDto mapToOfferResponseDto(OfferResponseObject offer) {
        return OfferResponseObjectDto.builder()
                .id(offer.id())
                .linkToOffer(offer.linkToOffer())
                .nameOfCompany(offer.nameOfCompany())
                .nameOfPosition(offer.nameOfPosition())
                .salary(offer.salary())
                .build();
    }
}