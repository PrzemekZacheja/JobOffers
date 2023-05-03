package pl.joboffers.domain.offer.dto;

import lombok.Builder;

@Builder
public record OfferResponseObjectDto(String nameOfJob, double salary, String description) {
}