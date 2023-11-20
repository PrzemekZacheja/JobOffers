package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

import java.util.List;

public class MapperOfferResponse {

    public static List<OfferGetResponseDto> mapToOfferGetResponseDto(List<Offer> allOffers) {
        return allOffers.stream()
                .map(MapperOfferResponse::mapToOfferGetResponseDto)
                .toList();
    }

    public static OfferGetResponseDto mapToOfferGetResponseDto(Offer offer) {
        return OfferGetResponseDto.builder()
                .id(offer.id())
                .offerUrl(offer.offerUrl())
                .company(offer.company())
                .title(offer.title())
                .salary(offer.salary())
                .build();
    }

    public static List<Offer> mapToOffer(List<OfferGetResponseDto> allOffers) {
        return allOffers.stream()
                .map(MapperOfferResponse::mapToOffer)
                .toList();
    }

    public static Offer mapToOffer(OfferGetResponseDto responseDto) {
        return Offer.builder()
                .id(responseDto.id())
                .title(responseDto.title())
                .company(responseDto.company())
                .salary(responseDto.salary())
                .offerUrl(responseDto.offerUrl())
                .build();
    }

    public static OfferPostResponseDto mapToOfferPostResponseDto(Offer offer) {
        return OfferPostResponseDto.builder()
                .offerUrl(offer.offerUrl())
                .company(offer.company())
                .title(offer.title())
                .salary(offer.salary())
                .build();

    }
}