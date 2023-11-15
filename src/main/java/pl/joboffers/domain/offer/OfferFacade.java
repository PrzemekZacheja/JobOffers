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
        List<Offer> offers = fetchUniqueOfferToDb();
        return repository.findAll()
                         .stream()
                         .map(MapperOfferResponse::mapToOfferGetResponseDto)
                         .collect(Collectors.toList());
    }

    private List<Offer> fetchUniqueOfferToDb() {
        List<OfferGetResponseDto> offerGetResponseDtos = client.fetchAllOfferFromForeignAPI();
        List<Offer> offerList = offerGetResponseDtos.stream()
                                                    .map(MapperOfferResponse::mapToOffer)
                                                    .toList();
        log.info("save " + offerList.size() + " offers");
        return saveUniqueOffers(offerList, repository);
    }

    private List<Offer> saveUniqueOffers(List<Offer> offerList, OfferFacadeRepository repository) {
        offerList.forEach(this::saveUniqueOfferToDb);
        log.info("saved " + repository.count() + " offers to db");
        return offerList;
    }

    public OfferPostResponseDto addManualJobOffer(OfferPostRequestDto offerRequestDto) {
        Offer offer = Offer.builder()
                           .offerUrl(offerRequestDto.offerUrl())
                           .title(offerRequestDto.title())
                           .company(offerRequestDto.company())
                           .salary(offerRequestDto.salary())
                           .build();
        return saveUniqueOfferToDb(offer);
    }

    private OfferPostResponseDto saveUniqueOfferToDb(Offer offer) {
        if (!repository.existsByOfferUrl(offer.offerUrl())) {
            Offer offerSaved = repository.save(offer);
            log.info("offer by url " + offer.offerUrl() + " saved to db");
            return MapperOfferResponse.mapToOfferPostResponseDto(offerSaved);
        } else {
            throw new OfferAlreadyExistException("Offer with url " + offer.offerUrl() + " already exist");
        }
    }

    public OfferGetResponseDto findOfferById(String id) {
        Offer offerById = repository.findById(id)
                                    .orElseThrow(() -> new NoOfferInDBException("Not found for id: " + id));
        return MapperOfferResponse.mapToOfferGetResponseDto(offerById);
    }

    public OfferGetResponseDto findByOfferUrl(String offerUrl) {
        Offer offerByUrl = repository.findByOfferUrl(offerUrl);
        return MapperOfferResponse.mapToOfferGetResponseDto(offerByUrl);
    }
}