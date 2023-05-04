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
        String link = "www.jobsforjuniors1.com";
        //when
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        OfferResponseObject byLinkAsId = repositoryForTest.findByLinkAsId(link);
        assertThat(allOffers.get(0)).isEqualTo(OfferResponseMapper.mapToOfferResponseDto(byLinkAsId));
    }

    @Test
    void should_return_correct_object_added_manualy() {
        //given
        String link = "www.jobsforjuniors1.com";
        String nameOfPosition = "Junior1";
        String nameOfCompany = "CBD1";
        double salary = 3500.00;
        //when
        OfferResponseObjectDto offerSavedManually = offerFacade.addManualJobOffer(link, nameOfPosition, nameOfCompany, salary);
        OfferResponseObject byLinkAsId = repositoryForTest.findByLinkAsId("www.jobsforjuniors1.com");
        //then
        assertThat(offerSavedManually).isEqualTo(OfferResponseMapper.mapToOfferResponseDto(byLinkAsId));
    }
}