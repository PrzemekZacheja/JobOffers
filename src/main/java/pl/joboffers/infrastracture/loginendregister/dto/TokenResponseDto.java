package pl.joboffers.infrastracture.loginendregister.dto;

import lombok.Builder;

@Builder
public record TokenResponseDto(
        String email,
        String token
) {
}