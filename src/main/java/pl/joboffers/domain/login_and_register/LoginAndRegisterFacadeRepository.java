package pl.joboffers.domain.login_and_register;

public interface LoginAndRegisterFacadeRepository {

    User save(User user);

    User findByEmail(String emailToSearchInDatabase);
}