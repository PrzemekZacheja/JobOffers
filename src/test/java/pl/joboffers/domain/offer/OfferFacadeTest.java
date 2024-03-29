package pl.joboffers.domain.offer;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostRequestDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OfferFacadeTest {

    OfferFacadeRepository repositoryForTest = new OfferFacadeRepositoryForTest();
    OfferFacade offerFacade = new OfferFacade(
            new OfferResponseClientImpl(repositoryForTest),
            repositoryForTest
    );


    @Test
    void should_return_dto_object() {
        //given
        //when
        offerFacade.fetchUniqueOfferToDb();
        //then
        assertThat(offerFacade.getAllOffers().get(0)).isInstanceOf(OfferGetResponseDto.class);
    }

    @Test
    void should_return_list_of_3_object_from_client() {
        //given
        //when
        offerFacade.fetchUniqueOfferToDb();
        List<OfferGetResponseDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers.size()).isEqualTo(3);
    }

    @Test
    void should_return_object_from_hash() {
        //given
        String link = "www.jobsforjuniors1.com";
        //when
        List<Offer> allOffers = offerFacade.fetchUniqueOfferToDb();
        //then
        Offer byLinkAsId =
                repositoryForTest.findByOfferUrl(link);
        assertThat(allOffers.get(0)).isEqualTo(byLinkAsId);
    }

    @Test
    void should_return_correct_object_added_manually() {
        //given
        String link = "www.jobsforjuniors1.com";
        String nameOfPosition = "Junior1";
        String nameOfCompany = "CBD1";
        String salary = "3500.00";
        OfferPostRequestDto requestDto = new OfferPostRequestDto(nameOfPosition, nameOfCompany, salary, link);
        //when
        OfferPostResponseDto offerSavedManually = offerFacade.addManualJobOffer(requestDto);
        Offer byLinkAsId =
                repositoryForTest.findByOfferUrl("www.jobsforjuniors1.com");
        //then
        assertThat(offerSavedManually).isEqualTo(MapperOfferResponse.mapToOfferPostResponseDto(byLinkAsId));
    }

    @Test
    void should_return_one_object_when_try_save_duplicates_by_link_of_offer() {
        //given
        OfferGetResponseDto offerGetResponseDto1 = new OfferGetResponseDto(null, "Junior1", "CBD1", "3500" +
                ".00", "www.jobsforjuniors1.com");
        OfferGetResponseDto offerGetResponseDto2 = new OfferGetResponseDto(null, "Junior1", "CBD1", "3500" +
                ".00", "www.jobsforjuniors1.com");
        //when
        repositoryForTest.save(MapperOfferResponse.mapToOffer(offerGetResponseDto1));
        repositoryForTest.save(MapperOfferResponse.mapToOffer(offerGetResponseDto2));
        //then
        List<Offer> allOffersFromRepository = repositoryForTest.findAll();
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }

    @Test
    void should_return_correct_object_find_by_link_to_offer() {
        //given
        String offerUrl = "www.jobsforjuniors3.com";
        OfferGetResponseDto expected = new OfferGetResponseDto("3", "Junior3", "CBD3", "5500" +
                ".00", "www.jobsforjuniors3.com");
        offerFacade.fetchUniqueOfferToDb();
        //when
        OfferGetResponseDto oneOfferById = offerFacade.findByOfferUrl(offerUrl);
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
        OfferPostRequestDto requestDto = new OfferPostRequestDto(nameOfPosition, nameOfCompany, salary, link);
        //when
        try {
            OfferPostResponseDto offerSavedManually1 = offerFacade.addManualJobOffer(requestDto);
            OfferPostResponseDto offerSavedManually2 = offerFacade.addManualJobOffer(requestDto);
        } catch (DuplicateKeyException e) {
            System.out.println("DuplicateKeyException");
        }
        //then
        assertThat(repositoryForTest.findAll()
                                    .size()).isEqualTo(1);
    }
}