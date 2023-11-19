package pl.joboffers.infrastracture.offer.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.joboffers.domain.offer.MapperOfferResponse;
import pl.joboffers.domain.offer.Offer;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class OfferScheduler {

    private final OfferFacade offerFacade;

    @Scheduled(cron = "${joboffers.offer.durationOfResponse}")
    public List<OfferGetResponseDto> scheduleFetchAllOffers() {
        log.info("Scheduling");
        List<Offer> allOffers = offerFacade.fetchUniqueOfferToDb();
        log.info("Fetching offers");
        log.info("Getting offers: " + allOffers.size());
        List<OfferGetResponseDto> offerGetResponseDtos = MapperOfferResponse.mapToOfferGetResponseDto(allOffers);
        return offerGetResponseDtos;
    }
}