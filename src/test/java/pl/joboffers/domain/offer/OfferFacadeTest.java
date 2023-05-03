package pl.joboffers.domain.offer;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OfferFacadeTest {

    OfferFacadeRepository repositoryForTest = new OfferFacadeRepositoryForTest();
    OfferFacade offerFacade = new OfferFacade(
            new OfferResponseClientImpl(),
            repositoryForTest);

    @Test
    void should_return_dto_object() {
        assertThat(offerFacade.getAllOffers().get(0)).isInstanceOf(OfferResponseObjectDto.class);
    }

    @Test
    void should_return_list_of_3_object_from_client() {
        //given
        //when
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers.size()).isEqualTo(3);
    }

    @Test
    void should_return_object_from_hash() {
        //given

        //when
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers.get(0)).isEqualTo(repositoryForTest.);
    }
}