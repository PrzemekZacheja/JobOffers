package pl.joboffers.domain.offer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OfferFacadeRepositoryForTest implements OfferFacadeRepository {

    Map<String, OfferResponseObject> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public OfferResponseObject save(OfferResponseObject offer) {
        databaseInMemory.put(offer.linkToOffer(), offer);
        return offer;
    }

    @Override
    public OfferResponseObject findByLinkAsId(String link) {
        return databaseInMemory.get(link);
    }

}