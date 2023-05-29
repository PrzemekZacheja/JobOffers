package pl.joboffers.domain.offer;

import lombok.Builder;

@Builder
record OfferResponseObject(String id, String linkToOffer, String nameOfPosition, String nameOfCompany, String salary) {
}