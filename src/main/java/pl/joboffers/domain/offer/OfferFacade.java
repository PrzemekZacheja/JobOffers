package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
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
        return repository.findAll()
                .stream()
                .map(MapperOfferResponse::mapToOfferGetResponseDto)
                .collect(Collectors.toList());
    }

    public List<Offer> fetchUniqueOfferToDb() {
        List<OfferGetResponseDto> offerGetResponseDtos = client.fetchAllOfferFromForeignAPI();
        List<Offer> offerList = offerGetResponseDtos.stream()
                .map(MapperOfferResponse::mapToOffer)
                .toList();
        List<Offer> filteredUniqueOffers = filterUniqueOffers(offerList);
        repository.saveAll(filteredUniqueOffers);
        return filteredUniqueOffers;
    }

    private List<Offer> filterUniqueOffers(List<Offer> offerList) {
        return offerList.stream()
                .filter(offer -> !offer.offerUrl().isEmpty())
                .filter(offer -> repository.existsByOfferUrl(offer.offerUrl()))
                .collect(Collectors.toList());
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
        if (repository.existsByOfferUrl(offer.offerUrl())) {
            Offer offerSaved = repository.save(offer);
            log.info("offer by url " + offer.offerUrl() + " saved to db");
            return MapperOfferResponse.mapToOfferPostResponseDto(offerSaved);
        } else {
            String message = "offer by url " + offer.offerUrl() + " already exist in db";
            try {
                throw new DuplicateKeyException(message);
            } catch (DuplicateKeyException e) {
                log.error(message);
            }
            return null;
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