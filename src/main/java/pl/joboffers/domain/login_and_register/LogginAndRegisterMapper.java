package pl.joboffers.domain.login_and_register;

import pl.joboffers.domain.login_and_register.dto.UserDto;

public class LogginAndRegisterMapper {


    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .password(userDto.password())
                .token(userDto.token())
                .email(userDto.email())
                .isLogged(userDto.isLogged())
                .build();
    }

    public static UserDto mapToUserDto(User userToMap) {
        return UserDto.builder()
                .password(userToMap.password())
                .token(userToMap.token())
                .email(userToMap.email())
                .isLogged(userToMap.isLogged())
                .build();

    }
}