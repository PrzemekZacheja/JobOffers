package pl.joboffers.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import pl.joboffers.domain.offer.dto.OfferPostResponseDto;
import pl.joboffers.infrastracture.offer.scheduler.OfferScheduler;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class JobOffersFetchedSuccessful extends BaseIntegrationTest implements SampleJobOffersResponse {

    @Autowired
    OfferFacade offerFacade;

    @Autowired
    OfferScheduler scheduler;

    @Test
    void should_fetch_all_job_offers_for_junior_save_to_repository_and_show_to_user() throws Exception {

//    step 1: there are no offers in external HTTP server (http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers)
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                                       .willReturn(WireMock.aResponse()
                                                           .withStatus(HttpStatus.OK.value())
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody(bodyWithZeroOffersJson())));
        //when
        List<OfferGetResponseDto> allOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffers).isEmpty();


//    step 2: scheduler ran 1st time and made GET to external server and system added 0 offers to database
        //given & when
        List<OfferGetResponseDto> savedOffers = scheduler.scheduleGetAllOffers();
        //then
        assertThat(savedOffers).isEmpty();

//    step 3: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned UNAUTHORIZED(401)
//    step 4: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//    step 5: user made POST /register with username=someUser, password=somePassword and system registered user with status OK(200)
//    step 6: user tried to get JWT token by requesting POST /token with username=someUser, password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC


//    step 7: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers
        //given
        String urlTemplate = "/offers";
        // when
        ResultActions perform = mockMvc.perform(get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        MvcResult mvcResult = perform.andExpect(status().isOk())
                                     .andReturn();
        String contentAsString = mvcResult.getResponse()
                                          .getContentAsString();
        List<OfferGetResponseDto> offerGetResponseDtoList = objectMapper.readValue(contentAsString,
                                                                                   new TypeReference<>() {
                                                                                   });
        //then
        assertThat(offerGetResponseDtoList).isEmpty();


//    step 8: there are 2 new offers in external HTTP server
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                                       .willReturn(WireMock.aResponse()
                                                           .withStatus(HttpStatus.OK.value())
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody(bodyWithTwoOffersJson())));
        //when
        List<OfferGetResponseDto> allOffersWithTwoOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffersWithTwoOffers).size()
                                          .isEqualTo(2);


//    step 9: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1000 and 2000 to database
        //given & when
        savedOffers = scheduler.scheduleGetAllOffers();
        //then
        assertThat(savedOffers).size()
                               .isEqualTo(2);


//    step 10: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1000 and 2000
        //given & when
        perform = mockMvc.perform(get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        mvcResult = perform.andExpect(status().isOk())
                           .andReturn();
        contentAsString = mvcResult.getResponse()
                                   .getContentAsString();
        List<OfferGetResponseDto> offerGetResponseDtos = objectMapper.readValue(contentAsString,
                                                                                new TypeReference<>() {
                                                                                });
        //then
        assertThat(offerGetResponseDtos).size()
                                        .isEqualTo(2);

//    step 11: user made GET /offers/9999 and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
        //given
        urlTemplate = "/offers/9999";
        //when
        ResultActions performGetOfferWithNoExistingId = mockMvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        performGetOfferWithNoExistingId.andExpect(status().isNotFound())
                                       .andExpect(content().json("""
                                                                           {
                                                                           "message": "Not found for id: 9999",
                                                                           "status" : "NOT_FOUND"
                                                                           }
                                                                         """.trim()));


//    step 12: user made GET /offers/1000 and system returned OK(200) with offer
        //given
        urlTemplate = "/offers/1000";
        //when
        ResultActions performGetOfferWithExistingId = mockMvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        performGetOfferWithExistingId.andExpect(status().isOk())
                                     .andExpect(content().json("""
                                                                       {
                                                                       "id": 1000,
                                                                       "title": "string Title",
                                                                       "company": "string Company",
                                                                       "salary": "string Salary",
                                                                       "offerUrl": "string OfferURL"
                                                                       }
                                                                       """.trim()));


//    step 13: there are 2 new offers in external HTTP server
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                                       .willReturn(WireMock.aResponse()
                                                           .withStatus(HttpStatus.OK.value())
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBody(bodyWithFourOffersJson())));
        //when
        allOffersWithTwoOffers = offerFacade.getAllOffers();
        //then
        assertThat(allOffersWithTwoOffers).size()
                                          .isEqualTo(4);


//    step 14: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 3000 and 4000 to database
        //given & when
        savedOffers = scheduler.scheduleGetAllOffers();
        //then
        assertThat(savedOffers).size()
                               .isEqualTo(2);
        assertThat(savedOffers.get(0)
                              .id()).isEqualTo(3000);
        assertThat(savedOffers.get(1)
                              .id()).isEqualTo(4000);


//    step 15: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 4 offers with ids: 1000,2000, 3000 and 4000
        //given & when
        perform = mockMvc.perform(get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        perform.andExpect(status().isOk())
               .andExpect(content().json("""
                                                 {   "id": 1000,
                                                     "title": "Junior Java Developer",
                                                     "company": "BlueSoft Sp. z o.o.",
                                                     "salary": "7 000 – 9 000 PLN",
                                                     "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre1"
                                                 },
                                                 {
                                                     "id": 2000,
                                                     "title": "Java (CMS) Developer",
                                                     "company": "Efigence SA",
                                                     "salary": "16 000 – 18 000 PLN",
                                                     "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh2"
                                                 },
                                                 {
                                                     "id": 3000,
                                                     "title": "Junior Java Developer",
                                                     "company": "Sollers Consulting",
                                                     "salary": "7 500 – 11 500 PLN",
                                                     "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-sollers-consulting-warszawa-s6et1ucc3"
                                                 }
                                                 {
                                                     "id": 4000,
                                                     "title": "Junior Java Developer",
                                                     "company": "Sollers Consulting",
                                                     "salary": "7 500 – 11 500 PLN",
                                                     "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-sollers-consulting-warszawa-s6et1ucc4"
                                                 }
                                                 """.trim()));


//    stet 16: user made POST /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and offers as body and system returned CREATED(201) with saved offer
        //given
        urlTemplate = "/offers";
        //when
        ResultActions performPostOffer = mockMvc.perform(post(urlTemplate).content("""
                                                                                             {
                                                                                                "title": "string Title",
                                                                                                "company": "string Company",
                                                                                                "salary": "string Salary",
                                                                                                "offerUrl": "string OfferURL"
                                                                                              }
                                                                                           """.trim())
                                                                          .contentType(
                                                                                  MediaType.APPLICATION_JSON_VALUE + ";" + "charset=UTF-8"));
        //then
        MvcResult creadtedMvcResult = performPostOffer.andExpect(status().isCreated())
                                                      .andReturn();
        String createdContentAsString = creadtedMvcResult.getResponse()
                                                         .getContentAsString();
        OfferPostResponseDto parsedOfferSavedDto = objectMapper.readValue(createdContentAsString, OfferPostResponseDto.class);
        assertAll(() -> assertThat(parsedOfferSavedDto.id()).isNotNull(),
                  () -> assertThat(parsedOfferSavedDto.title()).isEqualTo("string Title"),
                  () -> assertThat(parsedOfferSavedDto.company()).isEqualTo("string Company"),
                  () -> assertThat(parsedOfferSavedDto.salary()).isEqualTo("string Salary"),
                  () -> assertThat(parsedOfferSavedDto.offerUrl()).isEqualTo("string OfferURL"),
                  () -> assertThat(parsedOfferSavedDto.id()).isNotNull());


//    step 17: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) 1 offer
        //given
        //when
        ResultActions performGetOffer = mockMvc.perform(get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        String oneOfferJson = performGetOffer.andExpect(status().isOk())
                                             .andReturn()
                                             .getResponse()
                                             .getContentAsString();
        List<OfferGetResponseDto> listOfOffers = objectMapper.readValue(oneOfferJson, new TypeReference<>() {
        });
        assertThat(listOfOffers).hasSize(1);
    }
}