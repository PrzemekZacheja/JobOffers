package pl.joboffers.infrastracture.token.controller;

import lombok.Builder;

@Builder
public record TokenResponseDto(
        String email,
        String token
) {
}