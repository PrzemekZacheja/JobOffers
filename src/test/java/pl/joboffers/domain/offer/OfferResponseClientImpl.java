package pl.joboffers.domain.offer;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferResponseClientImpl implements OfferResponseClient {

    OfferFacadeRepository repositoryForTest;

    @Override
    public List<OfferGetResponseDto> fetchAllOfferFromForeignAPI() {
        List<OfferGetResponseDto> offerGetResponseDtos =
                List.of(
                        new OfferGetResponseDto("1",
                                                "Junior1",
                                                "CBD1",
                                                "3500.00",
                                                "www.jobsforjuniors1.com"),
                        new OfferGetResponseDto("2",
                                                "Junior2",
                                                "CBD2",
                                                "4500.00",
                                                "www.jobsforjuniors2.com"),
                        new OfferGetResponseDto("3",
                                                "Junior3",
                                                "CBD3",
                                                "5500.00",
                                                "www.jobsforjuniors3.com"));
        offerGetResponseDtos.forEach(dto -> repositoryForTest.save(MapperOfferResponse.mapToOffer(dto)));
        return repositoryForTest.findAll()
                                .stream()
                                .map(MapperOfferResponse::mapToOfferGetResponseDto)
                                .collect(Collectors.toList());
    }

}