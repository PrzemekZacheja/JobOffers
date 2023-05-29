package pl.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseObjectDto(String id, String linkToOffer, String nameOfPosition, String nameOfCompany,
                                     String salary) {

}