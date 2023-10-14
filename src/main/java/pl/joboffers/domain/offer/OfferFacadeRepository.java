package pl.joboffers.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

@Repository
public interface OfferFacadeRepository extends MongoRepository<Offer, String> {

    List<OfferGetResponseDto> findAllBy();


    // Optional<Offer> findOfferByLinkToOffer(String id);
}