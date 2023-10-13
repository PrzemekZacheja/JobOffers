package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostRequestDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

import java.util.List;

@AllArgsConstructor
public class OfferFacade {

    private final OfferResponseClient client;
    private final OfferFacadeRepository repository;
    private final HashGenerator hashGenerator;

    public List<OfferGetResponseDto> getAllOffers() {
        List<OfferGetResponseDto> allOffers = client.getAllOffers();
        return allOffers.stream()
                        .map(MapperOfferResponse::mapToOfferResponse)
                        .map(repository::save)
                        .map(MapperOfferResponse::mapToOfferGetResponseDto)
                        .toList();
    }

    public OfferPostResponseDto addManualJobOffer(String linkToOffer,
                                                  String nameOfPosition,
                                                  String nameOfCompany,
                                                  String salary) {
        OfferResponse offerResponse = OfferResponse.builder()
                                                   .id(hashGenerator.getHash())
                                                   .linkToOffer(linkToOffer)
                                                   .nameOfPosition(nameOfPosition)
                                                   .nameOfCompany(nameOfCompany)
                                                   .salary(salary)
                                                   .build();
        OfferResponse save = repository.save(offerResponse);
        OfferResponse saved =
                repository.findOfferByLinkToOffer(offerResponse.linkToOffer())
                          .orElseThrow(() -> new NoOfferInDBException("Not found for link: " + offerResponse.linkToOffer()));
        return MapperOfferResponse.mapToOfferPostResponseDto(saved);
    }

    public OfferPostResponseDto addManualJobOfferByObject(OfferPostRequestDto offerRequestDto) {
        return addManualJobOffer(offerRequestDto.offerUrl(),
                                 offerRequestDto.title(),
                                 offerRequestDto.company(),
                                 offerRequestDto.salary());
    }

    public List<OfferGetResponseDto> getAllOffersFromRepository() {
        List<OfferResponse> allOffersFromRepository = repository.findAll();
        return allOffersFromRepository.stream()
                                      .map(MapperOfferResponse::mapToOfferGetResponseDto)
                                      .toList();
    }

    public OfferGetResponseDto findOfferById(String id) {
        OfferResponse offerById =
                repository.findOfferByLinkToOffer(id)
                          .orElseThrow(() -> new NoOfferInDBException("Not found for id: " + id));
        return MapperOfferResponse.mapToOfferGetResponseDto(offerById);
    }
}