package pl.joboffers.domain.login_and_register;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.login_and_register.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    LoginAndRegisterFacadeRepository repository;

    public UserDto registerUserOrLogin() {
        UserDto userDto = UserDto.builder()
                .token("exampleToken")
                .email("example@gov.pl")
                .isLogged(true)
                .password("1234")
                .build();
        repository.save(LogginAndRegisterMapper.mapToUser(userDto));

        return userDto;
    }
}