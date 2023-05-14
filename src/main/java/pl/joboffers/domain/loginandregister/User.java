package pl.joboffers.domain.loginandregister;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
final class User {
    private String email;
    private String password;
    private String token;
    private boolean isLogged;

    public void loggIn() {
        isLogged = true;
    }

}