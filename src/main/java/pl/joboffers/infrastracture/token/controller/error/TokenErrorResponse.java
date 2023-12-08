package pl.joboffers.infrastracture.token.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message, HttpStatus httpStatus) {
}