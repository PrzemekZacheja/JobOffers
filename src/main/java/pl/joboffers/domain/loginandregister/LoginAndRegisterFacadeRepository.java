package pl.joboffers.domain.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginAndRegisterFacadeRepository extends MongoRepository<User, String> {

    User save(User user);

    Optional<User> findByEmail(String emailToSearchInDatabase);
}