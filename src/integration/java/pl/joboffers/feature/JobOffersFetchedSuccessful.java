package pl.joboffers.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferResponseObjectDto;
import pl.joboffers.infrastracture.offer.scheduler.OfferScheduler;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JobOffersFetchedSuccessful extends BaseIntegrationTest implements SampleJobOffersResponse {

    @Autowired
    OfferFacade offerFacade;

    @Autowired
    OfferScheduler scheduler;

    @Test
    void should_fetch_all_job_offers_for_junior_save_to_repository_and_show_to_user() {

//    step 1: there are no offers in external HTTP server (http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers)
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                                       .willReturn(WireMock.aResponse()
                                                           .withStatus(HttpStatus.OK.value())
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody(bodyWithZeroOffersJson())));
        //when
        List<OfferResponseObjectDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers.size()).isEqualTo(0);


//    step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
        scheduler.scheduleGetAllOffers();


//    step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)
//    step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//    step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//    step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
//    step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
//    step 8: there are 2 new offers in external HTTP server
//    step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
//    step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
//    step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
//    step 12: user made GET /offers/1000 and system returned OK(200) with offer
//    step 13: there are 2 new offers in external HTTP server
//    step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
//    step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000
//
    }
}