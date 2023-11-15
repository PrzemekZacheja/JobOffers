package pl.joboffers.infrastracture.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ValidationErrorDto(List<String> messages, HttpStatus status) {
}