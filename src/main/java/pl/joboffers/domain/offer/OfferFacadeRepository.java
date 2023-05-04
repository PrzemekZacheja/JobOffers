package pl.joboffers.domain.offer;

public interface OfferFacadeRepository {
    OfferResponseObject save(OfferResponseObject offer);

    OfferResponseObject findByLinkAsId(String link);
}