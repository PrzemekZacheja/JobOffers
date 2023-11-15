package pl.joboffers.domain.loginandregister;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.loginandregister.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final LoginAndRegisterFacadeRepository repository;

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

    public UserDto loginUser(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new NoUserFoundInRepositoryException(email));
        user.loggIn();
        return MapperLoginAndRegister.mapToUserDto(user);
    }

    private boolean isSavedInRepository(String email) {
        return repository.findByEmail(email).isPresent();
    }
}