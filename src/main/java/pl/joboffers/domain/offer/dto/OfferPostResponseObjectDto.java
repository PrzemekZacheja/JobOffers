package pl.joboffers.domain.offer.dto;

public record OfferPostResponseObjectDto(
        String id,
        String title,
        String company,
        String salary,
        String offerUrl
) {
}