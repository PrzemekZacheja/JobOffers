package pl.joboffers.cache.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.domain.loginandregister.dto.ResultRegistrationDto;
import pl.joboffers.domain.offer.OfferFacade;
import pl.joboffers.infrastracture.loginendregister.dto.TokenResponseDto;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RedisOffersCacheIntegrationTest extends BaseIntegrationTest {

    @Container
    private static final GenericContainer<?> REDIS;

    static {
        REDIS = new GenericContainer<>("redis").withExposedPorts(6379);
        REDIS.start();
    }

    @SpyBean
    OfferFacade offerFacade;

    @Autowired
    CacheManager cacheManager;

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.redis.host", () -> REDIS.getFirstMappedPort().toString());
        registry.add("spring.cache.type", () -> "redis");
        registry.add("sprig.cache.redis.time-to-leave", () -> "PTS10S");
    }

    @Test
    void shouldCacheOffers() throws Exception {
        //    step 1: user made POST /register with email=someUser, password=somePassword and system registered user with status CREATED(201)
        //given && when
        ResultActions registerPerform = mockMvc.perform(post("/register").content("""
                                                                                  {
                                                                                  "email": "someUser",
                                                                                  "password": "somePassword"
                                                                                  }
                                                                                  """.trim()).contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        ResultActions resultActions = registerPerform.andExpect(status().isCreated());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ResultRegistrationDto resultRegistrationDto = objectMapper.readValue(contentAsString, ResultRegistrationDto.class);

        assertAll(() -> assertThat(resultRegistrationDto.email()).isEqualTo("someUser"),
                  () -> assertThat(resultRegistrationDto.isLogged()).isFalse());


        //    step2: user makes POST /token with email=someUser to login
        //    password=somePassword and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
        //given & when
        ResultActions successGeneratedToken = mockMvc.perform(post("/token").content("""
                                                                                     {
                                                                                       "email": "someUser",
                                                                                       "password": "somePassword"
                                                                                     }
                                                                                        """.trim())
                                                                            .contentType(MediaType.APPLICATION_JSON_VALUE + ";" + "charset=UTF-8"));

        //then
        ResultActions successResultToken = successGeneratedToken.andExpect(status().isOk());
        String contentAsStringToken = successResultToken.andReturn().getResponse().getContentAsString();
        TokenResponseDto tokenResponseDto = objectMapper.readValue(contentAsStringToken, TokenResponseDto.class);
        assertThat(tokenResponseDto.email()).isEqualTo("someUser");
        String token = tokenResponseDto.token();

        // step 3. should save to cache offers
        //given & when
        mockMvc.perform(get("/offers").header("Authorization", "Bearer " + token)
                                      .contentType(MediaType.APPLICATION_JSON_VALUE));
        //then
        verify(offerFacade, times(1)).getAllOffers();
        assertThat(cacheManager.getCacheNames().contains("jobOffers")).isTrue();

        // step 4. cache should be invalidated
        //given & when
        await().atMost(Duration.ofSeconds(4))
               .pollInterval(Duration.ofSeconds(1))
               .untilAsserted(() -> {
                   mockMvc.perform(get("/offers").header("Authorization", "Bearer " + token)
                                                 .contentType(MediaType.APPLICATION_JSON_VALUE));
                   verify(offerFacade, atLeast(2)).getAllOffers();
               });
    }
}