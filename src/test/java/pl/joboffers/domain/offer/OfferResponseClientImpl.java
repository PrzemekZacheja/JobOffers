package pl.joboffers.domain.offer;

import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

public class OfferResponseClientImpl implements OfferResponseClient {
    @Override
    public List<OfferResponseObjectDto> getAllOffers() {
        return List.of(
                new OfferResponseObjectDto("JobOffer1", 3500.00, "Description1"),
                new OfferResponseObjectDto("JobOffer2", 3600.00, "Description2"),
                new OfferResponseObjectDto("JobOffer3", 3700.00, "Description3")
        );
    }
}