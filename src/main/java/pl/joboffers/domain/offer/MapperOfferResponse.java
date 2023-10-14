package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

public class MapperOfferResponse {

/*    public static Offer mapToOfferResponse(OfferGetResponseDto offer) {
        return offer.builder()
                            .id(offer.id())
                            .offerUrl(offer.offerUrl())
                            .company(offer.company())
                            .nameOfPosition(offer.title())
                            .salary(offer.salary())
                            .build();
    }

    public static OfferGetResponseDto mapToOfferGetResponseDto(OfferResponse offer) {
        return OfferGetResponseDto.builder()
                                  .id(offer.id())
                                  .offerUrl(offer.linkToOffer())
                                  .company(offer.nameOfCompany())
                                  .title(offer.nameOfPosition())
                                  .salary(offer.salary())
                                  .build();
    }*/

    public static OfferPostResponseDto mapToOfferPostResponseDto(Offer offer) {
        return OfferPostResponseDto.builder()
                                   .id(offer.id())
                                   .offerUrl(offer.offerUrl())
                                   .company(offer.company())
                                   .title(offer.title())
                                   .salary(offer.salary())
                                   .build();

    }
}