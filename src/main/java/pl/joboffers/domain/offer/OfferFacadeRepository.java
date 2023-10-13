package pl.joboffers.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferFacadeRepository extends MongoRepository<OfferResponse, String> {

    Optional<OfferResponse> findOfferByLinkToOffer(String id);
}