package pl.joboffers.apivalidationerror;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.infrastracture.apivalidation.ValidationErrorDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void should_return_400_and_validation_error_message_when_request_is_empty() throws Exception {
        //given
        String urlTemplate = "/offers";
        //when
        ResultActions performPostOffer = mockMvc.perform(post(urlTemplate).content("""
                                                                                             {
                                                                                              }
                                                                                           """.trim())
                                                                          .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = performPostOffer.andExpect(status().isBadRequest())
                                              .andReturn();
        String contentAsString = mvcResult.getResponse()
                                          .getContentAsString();
        ValidationErrorDto validationErrorDto = objectMapper.readValue(contentAsString, ValidationErrorDto.class);
        Assertions.assertThat(validationErrorDto.messages())
                  .containsExactlyInAnyOrder("title must not be null",
                                             "company must not be null",
                                             "salary must not be null",
                                             "offerUrl must not be null",
                                             "title must not be empty",
                                             "salary must not be empty",
                                             "company must not be empty",
                                             "offerUrl must not be empty");
    }

    @Test
    public void should_return_400_and_validation_error_message_when_request_has_empty_fields() throws Exception {
        //given
        String urlTemplate = "/offers";
        //when
        ResultActions performPostOffer = mockMvc.perform(post(urlTemplate).content("""
                                                                                             {
                                                                                                 "company": "",
                                                                                                 "offerUrl": "",
                                                                                                 "salary": "",
                                                                                                 "title": ""
                                                                                               }
                                                                                           """.trim())
                                                                          .contentType(MediaType.APPLICATION_JSON));
        //then
        String contentAsString = performPostOffer.andExpect(status().isBadRequest())
                                                 .andReturn()
                                                 .getResponse()
                                                 .getContentAsString();
        ValidationErrorDto validationErrorDto = objectMapper.readValue(contentAsString, ValidationErrorDto.class);
        Assertions.assertThat(validationErrorDto.messages())
                  .containsExactlyInAnyOrder(
                          "title must not be empty",
                          "salary must not be empty",
                          "company must not be empty",
                          "offerUrl must not be empty");
    }

}