package pl.joboffers.domain.offer;

import java.util.List;

public interface OfferFacadeRepository {
    OfferResponseObject save(OfferResponseObject offer);

    OfferResponseObject findByLinkAsId(String link);

    List<OfferResponseObject> getAllOffersFromRepository();

    OfferResponseObject findOfferById(String id);
}