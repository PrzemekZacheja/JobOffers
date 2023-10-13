package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

public class MapperOfferResponse {

    public static OfferResponse mapToOfferResponse(OfferGetResponseDto offer) {
        return OfferResponse.builder()
                            .id(offer.id())
                            .linkToOffer(offer.offerUrl())
                            .nameOfCompany(offer.company())
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
    }

    public static OfferPostResponseDto mapToOfferPostResponseDto(OfferResponse offerResponse) {
        return OfferPostResponseDto.builder()
                                   .id(offerResponse.id())
                                   .offerUrl(offerResponse.linkToOffer())
                                   .company(offerResponse.nameOfCompany())
                                   .title(offerResponse.nameOfPosition())
                                   .salary(offerResponse.salary())
                                   .build();

    }
}