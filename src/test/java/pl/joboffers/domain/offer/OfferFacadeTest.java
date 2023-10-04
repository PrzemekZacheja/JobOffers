package pl.joboffers.domain.offer;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.offer.dto.OfferGetResponseObjectDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OfferFacadeTest {

    OfferFacadeRepository repositoryForTest = new OfferFacadeRepositoryForTest();
    OfferFacade offerFacade = new OfferFacade(
            new OfferResponseClientImpl(repositoryForTest),
            repositoryForTest,
            new HashGenerator());

    @Test
    void should_return_dto_object() {
        assertThat(offerFacade.getAllOffers()
                              .get(0)).isInstanceOf(OfferGetResponseObjectDto.class);
    }

    @Test
    void should_return_list_of_3_object_from_client() {
        //given
        //when
        List<OfferGetResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers.size()).isEqualTo(3);
    }

    @Test
    void should_return_object_from_hash() {
        //given
        String link = "www.jobsforjuniors1.com";
        //when
        List<OfferGetResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        OfferResponseObject byLinkAsId =
                repositoryForTest.findOfferById(link)
                        .orElseThrow(() -> new NoOfferInDBException("No Offer"));
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
        OfferGetResponseObjectDto offerSavedManually = offerFacade.addManualJobOffer(link,
                                                                                     nameOfPosition,
                                                                                     nameOfCompany,
                                                                                     salary);
        OfferResponseObject byLinkAsId =
                repositoryForTest.findOfferById("www.jobsforjuniors1.com")
                        .orElseThrow(() -> new NoOfferInDBException("No offer"));
        //then
        assertThat(offerSavedManually).isEqualTo(MapperOfferResponse.mapToOfferResponseDto(byLinkAsId));
    }

    @Test
    void should_return_one_object_when_try_save_duplicates_by_link_of_offer() {
        //given
        OfferGetResponseObjectDto offerGetResponseObjectDto1 = new OfferGetResponseObjectDto(null, "Junior1", "CBD1", "3500" +
                ".00", "www.jobsforjuniors1.com");
        OfferGetResponseObjectDto offerGetResponseObjectDto2 = new OfferGetResponseObjectDto(null, "Junior1", "CBD1", "3500" +
                ".00", "www.jobsforjuniors1.com");
        //when
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerGetResponseObjectDto1));
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerGetResponseObjectDto2));
        //then
        List<OfferResponseObject> allOffersFromRepository = repositoryForTest.findAll();
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }

    @Test
    void should_return_correct_object_find_by_link_to_offer() {
        //given
        String linkToOffer = "www.jobsforjuniors3.com";
        OfferGetResponseObjectDto expected = new OfferGetResponseObjectDto("3", "Junior3", "CBD3", "5500" +
                ".00", "www.jobsforjuniors3.com");
        List<OfferGetResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //when
        OfferGetResponseObjectDto oneOfferById = offerFacade.findOfferById(linkToOffer);
        //then
        assertThat(oneOfferById).isEqualTo(expected);
    }

    @Test
    void should_return_one_object_added_manually_two_times_equals_object_offer() {
        //given
        String link = "www.jobsforjuniors1.com";
        String nameOfPosition = "Junior1";
        String nameOfCompany = "CBD1";
        String salary = "3500.00";
        //when
        OfferGetResponseObjectDto offerSavedManually1 = offerFacade.addManualJobOffer(link,
                                                                                      nameOfPosition,
                                                                                      nameOfCompany,
                                                                                      salary);
        OfferGetResponseObjectDto offerSavedManually2 = offerFacade.addManualJobOffer(link,
                                                                                      nameOfPosition,
                                                                                      nameOfCompany,
                                                                                      salary);
        List<OfferGetResponseObjectDto> allOffersFromRepository = offerFacade.getAllOffersFromRepository();

        //then
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }
}