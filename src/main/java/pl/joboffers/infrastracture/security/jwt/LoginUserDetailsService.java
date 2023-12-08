package pl.joboffers.infrastracture.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.joboffers.domain.login.LoginAndRegisterFacade;
import pl.joboffers.domain.login.dto.UserDto;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;

    @Override
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
        UserDto userDto = loginAndRegisterFacade.findByEmail(email);
        return getUser(userDto);
    }

    private User getUser(UserDto userDto) {
        return new User(userDto.email(), userDto.password(), Collections.emptyList());
    }
}