package pl.joboffers.domain.login_and_register;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.login_and_register.dto.UserDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacadeRepository repository = new LoginAndRegisterFacadeRepositoryForTest();
    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(repository);

    @Test
    void should_save_user_to_database_if_not_exist() {
        //given
        User userToSaveInDatabase = User.builder()
                .email("example@gov.pl")
                .token("exampleToken")
                .isLogged(true)
                .password("1234")
                .build();
        String emailToSearchInDatabase = "example@gov.pl";
        //when
        UserDto registeredUser = loginAndRegisterFacade.registerUserOrLogin();
        //then
        User userFindByEmail = repository.findByEmail(emailToSearchInDatabase);
        assertThat(registeredUser).isEqualTo(LogginAndRegisterMapper.mapToUserDto(userFindByEmail));
    }
}