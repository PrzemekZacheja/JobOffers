package pl.joboffers.infrastracture.offer.controller.error;

import org.springframework.http.HttpStatus;

public record OfferErrorResponseDto(String message, HttpStatus status) {
}