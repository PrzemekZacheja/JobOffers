package pl.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseObjectDto(String linkToOffer, String nameOfPosition, String nameOfCompany, double salary) {
}