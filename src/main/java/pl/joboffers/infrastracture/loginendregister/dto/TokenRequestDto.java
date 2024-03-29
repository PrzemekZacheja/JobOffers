package pl.joboffers.infrastracture.loginendregister.dto;

import javax.validation.constraints.NotBlank;

public record TokenRequestDto(
        @NotBlank(message = "{email.not.blank}")
        String email,
        @NotBlank(message = "{password.not.blank}")
        String password
) {
}