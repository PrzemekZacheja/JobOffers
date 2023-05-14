package pl.joboffers.domain.loginandregister;

public interface LoginAndRegisterFacadeRepository {

    User save(User user);

    User findByEmail(String emailToSearchInDatabase);
}