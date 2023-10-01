package pl.joboffers.domain.offer;

public class NoOfferInDBException extends RuntimeException {

    public NoOfferInDBException(String message) {
        super(message);
    }
}