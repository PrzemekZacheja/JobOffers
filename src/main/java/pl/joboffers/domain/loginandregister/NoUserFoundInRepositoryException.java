package pl.joboffers.domain.loginandregister;

public class NoUserFoundInRepositoryException extends RuntimeException {
    public NoUserFoundInRepositoryException(String message) {
        super(message);
    }
}