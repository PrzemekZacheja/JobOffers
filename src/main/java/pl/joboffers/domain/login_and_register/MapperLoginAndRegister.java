package pl.joboffers.domain.login_and_register;

import pl.joboffers.domain.login_and_register.dto.UserDto;

public class MapperLoginAndRegister {


    public static User mapToUser(UserDto userDtoToMap) {
        return User.builder()
                .password(userDtoToMap.password())
                .token(userDtoToMap.token())
                .email(userDtoToMap.email())
                .isLogged(userDtoToMap.isLogged())
                .build();
    }

    public static UserDto mapToUserDto(User userToMap) {
        return UserDto.builder()
                .password(userToMap.getPassword())
                .token(userToMap.getToken())
                .email(userToMap.getEmail())
                .isLogged(userToMap.isLogged())
                .build();

    }
}