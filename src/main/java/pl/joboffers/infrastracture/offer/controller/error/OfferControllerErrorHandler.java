package pl.joboffers.infrastracture.offer.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joboffers.domain.offer.NoOfferInDBException;
import pl.joboffers.domain.offer.OfferAlreadyExistException;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;


@ControllerAdvice
@Log4j2
public class OfferControllerErrorHandler {

    @ExceptionHandler(NoOfferInDBException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOfferNotFoundException(NoOfferInDBException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(OfferAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<OfferPostResponseDto> handleOfferNotFoundException(OfferAlreadyExistException e) {
        log.error(e.getMessage());
        return ResponseEntity.notFound()
                             .build();
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleOfferDuplicatedException(org.springframework.dao.DuplicateKeyException e) {
        String message = " Offer already exist";
        log.warn(message);
        return e.getMessage() + message;
    }
}