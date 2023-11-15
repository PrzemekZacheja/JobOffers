package pl.joboffers.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferFacadeRepository extends MongoRepository<Offer, String> {


    Offer findByOfferUrl(String offerUrl);

    boolean existsByOfferUrl(String offerUrl);

}