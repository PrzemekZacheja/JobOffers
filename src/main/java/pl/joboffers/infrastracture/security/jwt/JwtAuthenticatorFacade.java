package pl.joboffers.infrastracture.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.joboffers.infrastracture.loginendregister.dto.TokenRequestDto;
import pl.joboffers.infrastracture.loginendregister.dto.TokenResponseDto;

@Component
@AllArgsConstructor
public class JwtAuthenticatorFacade {

    private final AuthenticationManager authenticationManager;

    public TokenResponseDto authenticate(TokenRequestDto tokenRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                tokenRequestDto.email(),
                tokenRequestDto.password()));
        return TokenResponseDto.builder().build();
    }

}