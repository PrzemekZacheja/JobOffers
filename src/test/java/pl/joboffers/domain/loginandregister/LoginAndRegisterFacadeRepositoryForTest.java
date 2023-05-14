package pl.joboffers.domain.loginandregister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAndRegisterFacadeRepositoryForTest implements LoginAndRegisterFacadeRepository {

    Map<String, User> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        databaseInMemory.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User findByEmail(String emailToSearchInDatabase) {
        return databaseInMemory.get(emailToSearchInDatabase);
    }
}