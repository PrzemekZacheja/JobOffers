package pl.joboffers.controller.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.joboffers.BaseIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DuplicateOfferByUrlExceptionIntegrationTest extends BaseIntegrationTest {

    String urlTemplate = "/offers";

    @Test
    public void should_return_409_conflict_when_post_two_identical_offers() throws Exception {
        //step 1:
        //given && when
        ResultActions performFirstPostOffer = mockMvc.perform(post(urlTemplate).content("""
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
        performFirstPostOffer.andExpect(status().isCreated());


        //step 2:
        //given && when
        ResultActions performSecondPostOffer = mockMvc.perform(post(urlTemplate).content("""
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
        performSecondPostOffer.andExpect(status().isConflict());
    }
}