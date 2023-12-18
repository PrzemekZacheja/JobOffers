package pl.joboffers.infrastracture.loginendregister.controller;

import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joboffers.infrastracture.loginendregister.dto.TokenRequestDto;
import pl.joboffers.infrastracture.loginendregister.dto.TokenResponseDto;
import pl.joboffers.infrastracture.security.jwt.JwtAuthenticatorFacade;

import javax.validation.Valid;

@RestController
@Builder
public class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> authenticateAndGenerateToken(@Valid @RequestBody TokenRequestDto tokenRequest) {
        TokenResponseDto tokenResponseDto = jwtAuthenticatorFacade.authenticate(tokenRequest);
        return ResponseEntity.ok(tokenResponseDto);
    }
}