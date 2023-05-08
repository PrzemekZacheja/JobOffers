package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {

    OfferResponseClient client;
    OfferFacadeRepository repository;
    private HashGenerator hashGenerator;

    public List<OfferResponseObjectDto> getAllOffers() {
        List<OfferResponseObjectDto> allOffers = client.getAllOffers();
        allOffers.forEach(offer -> repository.save(MapperOfferResponse.mapToOfferResponse(offer)));
        return allOffers;
    }

    public OfferResponseObjectDto addManualJobOffer(String linkToOffer, String nameOfPosition, String nameOfCompany, double salary) {
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
        List<OfferResponseObject> allOffersFromRepository = repository.getAllOffersFromRepository();
        return allOffersFromRepository.stream().map(MapperOfferResponse::mapToOfferResponseDto).collect(Collectors.toList());
    }

}