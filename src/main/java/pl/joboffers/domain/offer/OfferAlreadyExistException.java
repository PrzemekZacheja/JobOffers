package pl.joboffers.domain.offer;

public class OfferAlreadyExistException extends RuntimeException {
    public OfferAlreadyExistException(String message) {
        super(message);
    }
}