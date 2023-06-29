package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private final OfferResponseClient client;
    private final OfferFacadeRepository repository;
    private final HashGenerator hashGenerator;

    public List<OfferResponseObjectDto> getAllOffers() {
        List<OfferResponseObjectDto> allOffers = client.getAllOffers();
        return allOffers.stream()
                .map(MapperOfferResponse::mapToOfferResponse)
                .map(repository::save)
                .map(MapperOfferResponse::mapToOfferResponseDto)
                .toList();
    }

    public OfferResponseObjectDto addManualJobOffer(String linkToOffer,
                                                    String nameOfPosition,
                                                    String nameOfCompany,
                                                    String salary) {
        OfferResponseObject offerResponseObject = OfferResponseObject.builder()
                                                                     .id(hashGenerator.getHash())
                                                                     .linkToOffer(linkToOffer)
                                                                     .nameOfPosition(nameOfPosition)
                                                                     .nameOfCompany(nameOfCompany)
                                                                     .salary(salary)
                                                                     .build();
        repository.save(offerResponseObject);
        return MapperOfferResponse.mapToOfferResponseDto(offerResponseObject);
    }

    public List<OfferResponseObjectDto> getAllOffersFromRepository() {
        List<OfferResponseObject> allOffersFromRepository = repository.findAll();
        return allOffersFromRepository.stream()
                .map(MapperOfferResponse::mapToOfferResponseDto)
                .toList();
    }

    public OfferResponseObjectDto findOfferByLinkToOffer(String linkToOffer) {
        OfferResponseObject offerById =
                repository.findOfferByLinkToOffer(linkToOffer)
                        .orElseThrow(() -> new NoOfferInDBException("No offer"));
        return MapperOfferResponse.mapToOfferResponseDto(offerById);
    }
}