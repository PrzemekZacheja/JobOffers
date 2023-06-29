package pl.joboffers.domain.offer;

public class NoOfferInDBException extends RuntimeException {

    public NoOfferInDBException(final String message) {
        super(message);
    }
}