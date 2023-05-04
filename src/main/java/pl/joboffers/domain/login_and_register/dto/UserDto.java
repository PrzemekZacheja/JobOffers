package pl.joboffers.domain.login_and_register.dto;

import lombok.Builder;

@Builder
public record UserDto(String email, String password, String token, boolean isLogged) {
}