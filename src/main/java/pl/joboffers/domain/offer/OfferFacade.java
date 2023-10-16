package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostRequestDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Log4j2
public class OfferFacade {

    private final OfferResponseClient client;
    private final OfferFacadeRepository repository;

    public List<OfferGetResponseDto> getAllOffers() {
        fetchUniqeOfferToDb();
        return repository.findAll()
                         .stream()
                         .map(MapperOfferResponse::mapToOfferGetResponseDto)
                         .collect(Collectors.toList());
    }

    private void fetchUniqeOfferToDb() {
        List<OfferGetResponseDto> offerGetResponseDtos = client.fetchAllUniqueOfferFromForeignAPI();
        List<Offer> newUniqueOffer =
                offerGetResponseDtos.stream()
                                    .filter(offerGetResponseDto -> repository.existsByOfferUrl(offerGetResponseDto.offerUrl()))
                                    .map(MapperOfferResponse::mapToOffer)
                                    .toList();
        repository.saveAll(newUniqueOffer);
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

    public OfferGetResponseDto findOfferById(String id) {
        Offer offerById =
                repository.findById(id)
                          .orElseThrow(() -> new NoOfferInDBException("Not found for id: " + id));
        return MapperOfferResponse.mapToOfferGetResponseDto(offerById);
    }
}