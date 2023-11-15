package pl.joboffers.infrastracture.offer.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joboffers.domain.offer.NoOfferInDBException;


@ControllerAdvice
@Log4j2
public class OfferControllerErrorHandler {

    @ExceptionHandler(NoOfferInDBException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OfferErrorResponseDto handleOfferNotFoundException(NoOfferInDBException e) {
        log.error(e.getMessage());
        return new OfferErrorResponseDto(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public OfferErrorResponseDto handleOfferDuplicatedException(DuplicateKeyException e) {
        String message = "Offer already exist";
        log.warn(message);
        return new OfferErrorResponseDto(message, HttpStatus.CONFLICT);
    }
}