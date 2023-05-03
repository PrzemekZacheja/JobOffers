package pl.joboffers.domain.offer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OfferFacadeRepositoryForTest implements OfferFacadeRepository {
    Map<String, OfferResponseObject> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public OfferResponseObject save(OfferResponseObject offer) {
        databaseInMemory.put(offer.nameOfJob(), offer);
        return offer;
    }

    public OfferResponseObject get(String nameOfJob) {
        return databaseInMemory.get(nameOfJob);
    }
}