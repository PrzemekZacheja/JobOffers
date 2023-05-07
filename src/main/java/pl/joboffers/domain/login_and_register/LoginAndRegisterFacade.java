package pl.joboffers.domain.login_and_register;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.login_and_register.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    LoginAndRegisterFacadeRepository repository;

    public UserDto registerUser(String email, String password) {
        boolean savedInRepository = isSavedInRepository(email);

        if (savedInRepository) {
            return loginUser(email);
        } else {
            User user = User.builder()
                    .token(TokenGenerator.generateToken())
                    .email(email)
                    .isLogged(false)
                    .password(password)
                    .build();

            repository.save(user);
            return MapperLoginAndRegister.mapToUserDto(user);
        }
    }

    private UserDto loginUser(String email) {
        User foundInRepositoryUser = repository.findByEmail(email);
        foundInRepositoryUser.loggIn();
        return MapperLoginAndRegister.mapToUserDto(foundInRepositoryUser);
    }

    private boolean isSavedInRepository(String email) {
        return repository.findByEmail(email) != null;
    }
}