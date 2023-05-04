package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

public class OfferResponseClientImpl implements OfferResponseClient {
    @Override
    public List<OfferResponseObjectDto> getAllOffers() {
        return List.of(
                new OfferResponseObjectDto("www.jobsforjuniors1.com", "Junior1", "CBD1", 3500.00),
                new OfferResponseObjectDto("www.jobsforjuniors2.com", "Junior2", "CBD2", 4500.00),
                new OfferResponseObjectDto("www.jobsforjuniors3.com", "Junior3", "CBD3", 5500.00)
        );
    }
}