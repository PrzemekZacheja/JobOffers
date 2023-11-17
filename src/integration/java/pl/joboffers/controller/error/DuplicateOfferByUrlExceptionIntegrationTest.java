package pl.joboffers.controller.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joboffers.BaseIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DuplicateOfferByUrlExceptionIntegrationTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("joboffers.offer.http.client.config.uri", () -> WIRE_MOCK_HOST);
        registry.add("joboffers.offer.http.client.config.port", () -> wireMockServer.getPort());
    }

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