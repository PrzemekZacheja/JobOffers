package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseObjectDto;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private final OfferResponseClient client;
    private final OfferFacadeRepository repository;
    private final HashGenerator hashGenerator;

    public List<OfferGetResponseObjectDto> getAllOffers() {
        List<OfferGetResponseObjectDto> allOffers = client.getAllOffers();
        return allOffers.stream()
                        .map(MapperOfferResponse::mapToOfferResponse)
                        .map(repository::save)
                        .map(MapperOfferResponse::mapToOfferResponseDto)
                        .toList();
    }

    public OfferGetResponseObjectDto addManualJobOffer(String linkToOffer,
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

    public OfferPostResponseObjectDto addManualJobOffer(OfferPostResponseObjectDto offer) {
        OfferResponseObject offerResponseObject = OfferResponseObject.builder()
                                                                     .id(hashGenerator.getHash())
                                                                     .linkToOffer(offer.offerUrl())
                                                                     .nameOfPosition(offer.title())
                                                                     .nameOfCompany(offer.company())
                                                                     .salary(offer.salary())
                                                                     .build();
        repository.save(offerResponseObject);
        return MapperOfferResponse.mapToOfferPostResponseObjectDto(offerResponseObject);
    }

    public List<OfferGetResponseObjectDto> getAllOffersFromRepository() {
        List<OfferResponseObject> allOffersFromRepository = repository.findAll();
        return allOffersFromRepository.stream()
                                      .map(MapperOfferResponse::mapToOfferResponseDto)
                                      .toList();
    }

    public OfferGetResponseObjectDto findOfferById(String id) {
        OfferResponseObject offerById =
                repository.findOfferById(id)
                          .orElseThrow(() -> new NoOfferInDBException("Not found for id: " + id));
        return MapperOfferResponse.mapToOfferResponseDto(offerById);
    }
}