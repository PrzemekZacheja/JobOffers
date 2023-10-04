package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferResponseClientImpl implements OfferResponseClient {

    OfferFacadeRepository repositoryForTest;

    @Override
    public List<OfferGetResponseObjectDto> getAllOffers() {
        List<OfferGetResponseObjectDto> offerGetResponseObjectDtos =
                List.of(
                        new OfferGetResponseObjectDto("1",
                                                      "Junior1",
                                                      "CBD1",
                                                      "3500.00",
                                                      "www.jobsforjuniors1.com"),
                        new OfferGetResponseObjectDto("2",
                                                      "Junior2",
                                                      "CBD2",
                                                      "4500.00",
                                                      "www.jobsforjuniors2.com"),
                        new OfferGetResponseObjectDto("3",
                                                      "Junior3",
                                                      "CBD3",
                                                      "5500.00",
                                                      "www.jobsforjuniors3.com"));
        offerGetResponseObjectDtos.forEach(dto -> repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(dto)));
        return repositoryForTest.findAll()
                .stream()
                .map(MapperOfferResponse::mapToOfferResponseDto)
                .collect(Collectors.toList());
    }

}