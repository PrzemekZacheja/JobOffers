package pl.joboffers.domain.offer;

import lombok.Builder;

@Builder
record OfferResponseObject(String linkToOffer, String nameOfPosition, String nameOfCompany, double salary) {
}