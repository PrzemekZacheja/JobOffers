package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

public class MapperOfferResponse {

    public static OfferResponseObject mapToOfferResponse(OfferResponseObjectDto offer) {
        return OfferResponseObject.builder()
                .id(offer.id())
                .linkToOffer(offer.offerUrl())
                .nameOfCompany(offer.company())
                .nameOfPosition(offer.title())
                .salary(offer.salary())
                .build();
    }

    public static OfferResponseObjectDto mapToOfferResponseDto(OfferResponseObject offer) {
        return OfferResponseObjectDto.builder()
                .id(offer.id())
                .offerUrl(offer.linkToOffer())
                .company(offer.nameOfCompany())
                .title(offer.nameOfPosition())
                .salary(offer.salary())
                .build();
    }
}