package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    OfferResponseClient client;
    OfferFacadeRepository repository;
    private HashGenerator hashGenerator;

    public List<OfferResponseObjectDto> getAllOffers() {
        List<OfferResponseObjectDto> allOffers = client.getAllOffers();
        allOffers.forEach(offer -> repository.save(OfferResponseMapper.mapToOfferResponse(offer)));
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
        return OfferResponseMapper.mapToOfferResponseDto(offerResponseObject);
    }
}