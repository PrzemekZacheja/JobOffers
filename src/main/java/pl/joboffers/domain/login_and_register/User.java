package pl.joboffers.domain.login_and_register;

import lombok.Builder;

@Builder
record User(String email, String password, String token, boolean isLogged) {
}