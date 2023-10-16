package pl.joboffers.domain.offer;

class OfferFacadeTest {

 /*   OfferFacadeRepository repositoryForTest = new OfferFacadeRepositoryForTest();
    OfferFacade offerFacade = new OfferFacade(
            new OfferResponseClientImpl(repositoryForTest),
            repositoryForTest,
            new HashGenerator());

    @Test
    void should_return_dto_object() {
        assertThat(offerFacade.fetchAllUniqueOfferFromForeignAPI()
                              .get(0)).isInstanceOf(OfferGetResponseDto.class);
    }

    @Test
    void should_return_list_of_3_object_from_client() {
        //given
        //when
        List<OfferGetResponseDto> allOffers = offerFacade.fetchAllUniqueOfferFromForeignAPI();
        //then
        assertThat(allOffers.size()).isEqualTo(3);
    }

    @Test
    void should_return_object_from_hash() {
        //given
        String link = "www.jobsforjuniors1.com";
        //when
        List<OfferGetResponseDto> allOffers = offerFacade.fetchAllUniqueOfferFromForeignAPI();
        //then
        OfferResponse byLinkAsId =
                repositoryForTest.findOfferByLinkToOffer(link)
                        .orElseThrow(() -> new NoOfferInDBException("No Offer"));
        assertThat(allOffers.get(0)).isEqualTo(MapperOfferResponse.mapToOfferGetResponseDto(byLinkAsId));
    }

    @Test
    void should_return_correct_object_added_manually() {
        //given
        String link = "www.jobsforjuniors1.com";
        String nameOfPosition = "Junior1";
        String nameOfCompany = "CBD1";
        String salary = "3500.00";
        //when
        OfferPostResponseDto offerSavedManually = offerFacade.addManualJobOffer(link,
                                                                                nameOfPosition,
                                                                                nameOfCompany,
                                                                                salary);
        OfferResponse byLinkAsId =
                repositoryForTest.findOfferByLinkToOffer("www.jobsforjuniors1.com")
                        .orElseThrow(() -> new NoOfferInDBException("No offer"));
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
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerGetResponseDto1));
        repositoryForTest.save(MapperOfferResponse.mapToOfferResponse(offerGetResponseDto2));
        //then
        List<OfferResponse> allOffersFromRepository = repositoryForTest.findAll();
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }

    @Test
    void should_return_correct_object_find_by_link_to_offer() {
        //given
        String linkToOffer = "www.jobsforjuniors3.com";
        OfferGetResponseDto expected = new OfferGetResponseDto("3", "Junior3", "CBD3", "5500" +
                ".00", "www.jobsforjuniors3.com");
        List<OfferGetResponseDto> allOffers = offerFacade.fetchAllUniqueOfferFromForeignAPI();
        //when
        OfferGetResponseDto oneOfferById = offerFacade.findOfferById(linkToOffer);
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
        OfferPostResponseDto offerSavedManually1 = offerFacade.addManualJobOffer(link,
                                                                                 nameOfPosition,
                                                                                 nameOfCompany,
                                                                                 salary);
        OfferPostResponseDto offerSavedManually2 = offerFacade.addManualJobOffer(link,
                                                                                 nameOfPosition,
                                                                                 nameOfCompany,
                                                                                 salary);
        List<OfferGetResponseDto> allOffersFromRepository = offerFacade.getAllOffersFromRepository();

        //then
        assertThat(allOffersFromRepository.size()).isEqualTo(1);
    }*/
}