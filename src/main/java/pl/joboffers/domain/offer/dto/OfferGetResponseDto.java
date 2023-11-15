package pl.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferGetResponseDto(
        String id,
        String title,
        String company,
        String salary,
        String offerUrl
) {

}