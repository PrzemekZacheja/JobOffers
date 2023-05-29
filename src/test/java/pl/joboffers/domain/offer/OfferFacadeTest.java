package pl.joboffers.domain.offer;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OfferFacadeTest {

    OfferFacadeRepository repositoryForTest = new OfferFacadeRepositoryForTest();
    OfferFacade offerFacade = new OfferFacade(
            new OfferResponseClientImpl(),
            repositoryForTest,
            new HashGenerator());

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
        assertThat(allOffers.get(0)).isEqualTo(MapperOfferResponse.mapToOfferResponseDto(byLinkAsId));
    }

    @Test
    void should_return_correct_object_added_manually() {
        //given
        String link = "www.jobsforjuniors1.com";
        String nameOfPosition = "Junior1";
        String nameOfCompany = "CBD1";
        String salary = "3500.00";
        //when
        OfferResponseObjectDto offerSavedManually = offerFacade.addManualJobOffer(link, nameOfPosition, nameOfCompany, salary);
        OfferResponseObject byLinkAsId = repositoryForTest.findByLinkAsId("www.jobsforjuniors1.com");
        //then
        assertThat(offerSavedManually).isEqualTo(MapperOfferResponse.mapToOfferResponseDto(byLinkAsId));
    }

    @Test
    void should_return_one_object_when_try_save_duplicates_by_link_of_offer() {
        //given
        OfferResponseObjectDto offerResponseObjectDto1 = new OfferResponseObjectDto("1", "www.jobsforjuniors1.com", "Junior1", "CBD1", "3500.00");
        OfferResponseObjectDto offerResponseObjectDto2 = new OfferResponseObjectDto("2", "www.jobsforjuniors1.com", "Junior2", "CBD2", "3500.00");
        //when
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerResponseObjectDto1));
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerResponseObjectDto2));
        //then
        List<OfferResponseObject> allOffersFromRepository = repositoryForTest.getAllOffersFromRepository();
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }

    @Test
    void should_return_correct_object_find_by_id() {
        //given
        String id = "3";
        OfferResponseObjectDto expected = new OfferResponseObjectDto("3", "www.jobsforjuniors3.com", "Junior3", "CBD3", "5500.00");
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //when
        OfferResponseObjectDto oneOfferById = offerFacade.findOneOfferById(id);
        //then
        assertThat(oneOfferById).isEqualTo(expected);
    }
}