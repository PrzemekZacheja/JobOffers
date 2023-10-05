package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseObjectDto;

public class MapperOfferResponse {

    public static OfferResponseObject mapToOfferResponse(OfferGetResponseObjectDto offer) {
        return OfferResponseObject.builder()
                .id(offer.id())
                .linkToOffer(offer.offerUrl())
                .nameOfCompany(offer.company())
                .nameOfPosition(offer.title())
                .salary(offer.salary())
                .build();
    }

    public static OfferGetResponseObjectDto mapToOfferResponseDto(OfferResponseObject offer) {
        return OfferGetResponseObjectDto.builder()
                                        .id(offer.id())
                                        .offerUrl(offer.linkToOffer())
                                        .company(offer.nameOfCompany())
                                        .title(offer.nameOfPosition())
                                        .salary(offer.salary())
                                        .build();
    }

    public static OfferPostResponseObjectDto mapToOfferPostResponseObjectDto(OfferResponseObject offerResponseObject) {
        return OfferPostResponseObjectDto.builder()
                                         .offerUrl(offerResponseObject.linkToOffer())
                                         .company(offerResponseObject.nameOfCompany())
                                         .title(offerResponseObject.nameOfPosition())
                                         .salary(offerResponseObject.salary())
                                         .build();

    }
}