package pl.joboffers.infrastracture.loginendregister.controller.error;

import org.springframework.http.HttpStatus;

public record TokenErrorResponse(String message, HttpStatus httpStatus) {
}