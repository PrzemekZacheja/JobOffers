package pl.joboffers.domain.loginandregister;

import java.util.Optional;

public interface LoginAndRegisterFacadeRepository {

    User save(User user);

    Optional<User> findByEmail(String emailToSearchInDatabase);
}