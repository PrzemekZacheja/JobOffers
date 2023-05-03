package pl.joboffers.domain.offer;

import lombok.Builder;

@Builder
record OfferResponseObject(String nameOfJob, double salary, String description) {
}

//TODO add id as long to OfferResponseObject and OfferResponseObjectDto