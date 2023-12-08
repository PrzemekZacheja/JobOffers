package pl.joboffers.domain.login;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginAndRegisterFacadeRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}