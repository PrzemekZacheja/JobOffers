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
        String email = "example@gov.pl";
        String password = "1234";
        //when
        UserDto registeredUser = loginAndRegisterFacade.registerUser(email, password);
        //then
        User userFindByEmail = repository.findByEmail(email);
        assertThat(registeredUser).isEqualTo(MapperLoginAndRegister.mapToUserDto(userFindByEmail));
    }

    @Test
    void should_create_User_and_set_isLogged_to_false_when_user_not_exist_in_repository() {
        //given
        String email = "example@gov.pl";
        String password = "1234";
        //when
        UserDto firstLogin = loginAndRegisterFacade.registerUser(email, password);
        //then
        assertThat(firstLogin.isLogged()).isFalse();
    }


    @Test
    void should_change_isLogged_to_true_when_user_exist_in_repository() {
        //given
        String email = "example@gov.pl";
        String password = "1234";
        //when
        UserDto firstLogin = loginAndRegisterFacade.registerUser(email, password);
        UserDto secondLogin = loginAndRegisterFacade.registerUser(email, password);
        //then
        assertThat(secondLogin.isLogged()).isTrue();
    }

}