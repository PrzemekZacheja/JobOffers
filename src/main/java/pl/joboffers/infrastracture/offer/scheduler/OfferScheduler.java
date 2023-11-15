package pl.joboffers.infrastracture.offer.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class OfferScheduler {

    private final OfferFacade offerFacade;

    @Scheduled(cron = "${joboffers.offer.durationOfResponse}")
    public List<OfferGetResponseDto> scheduleGetAllOffers() {
        log.info("Scheduling");
        List<OfferGetResponseDto> allOffers = offerFacade.getAllOffers();
        log.info(allOffers.size());
        return allOffers;
    }
}