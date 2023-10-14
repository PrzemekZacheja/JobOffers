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


    public OfferPostResponseDto addManualJobOffer(OfferPostRequestDto offerRequestDto) {
        Offer offer = Offer.builder()
                           .offerUrl(offerRequestDto.offerUrl())
                           .title(offerRequestDto.title())
                           .company(offerRequestDto.company())
                           .salary(offerRequestDto.salary())
                           .build();
        Offer offerSaved = repository.save(offer);
        log.info("offer save");
        return MapperOfferResponse.mapToOfferPostResponseDto(offerSaved);
    }

/*    public List<OfferGetResponseDto> getAllOffersFromRepository() {
        List<Offer> allOffersFromRepository = repository.findAll();
        return allOffersFromRepository.stream()
                                      .map(MapperOfferResponse::mapToOfferGetResponseDto)
                                      .toList();
    }

    public OfferGetResponseDto findOfferById(String id) {
        Offer offerById =
                repository.findOfferByLinkToOffer(id)
                          .orElseThrow(() -> new NoOfferInDBException("Not found for id: " + id));
        return MapperOfferResponse.mapToOfferGetResponseDto(offerById);
    }
}