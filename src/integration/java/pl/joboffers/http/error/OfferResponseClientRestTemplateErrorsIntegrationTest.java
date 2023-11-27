package pl.joboffers.http.error;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.server.ResponseStatusException;
import pl.joboffers.domain.offer.OfferResponseClient;
import pl.joboffers.feature.SampleJobOffersResponse;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class OfferResponseClientRestTemplateErrorsIntegrationTest implements SampleJobOffersResponse {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension
            .newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    OfferResponseClient offerResponseClient = new OfferResponseClientRestTemplateErrorsIntegrationTestConfig().remoteJobOfferClient(
            wireMockServer.getPort(),
            1000,
            1000);

    @Test
    void should_throw_exception_500_when_fault_connection_reset_by_peer() {
        //given
        wireMockServer.stubFor(WireMock
                                       .get("/offers")
                                       .willReturn(WireMock
                                                           .aResponse()
                                                           .withStatus(HttpStatus.SC_OK)
                                                           .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                                                           .withFault(Fault.CONNECTION_RESET_BY_PEER)));
        //when
        Throwable throwable = catchThrowable(() -> offerResponseClient.fetchAllOfferFromForeignAPI());
        //then
        assertThat(throwable).isExactlyInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");

    }

    @Test
    void should_throw_exception_500_when_fault_empty_response_body() {
        //given
        wireMockServer.stubFor(WireMock
                                       .get("/offers")
                                       .willReturn(WireMock
                                                           .aResponse()
                                                           .withStatus(HttpStatus.SC_OK)
                                                           .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                                                           .withFault(Fault.EMPTY_RESPONSE)));
        //when
        Throwable throwable = catchThrowable(() -> offerResponseClient.fetchAllOfferFromForeignAPI());
        //then
        assertThat(throwable).isExactlyInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");

    }

    @Test
    void should_throw_exception_500_when_fault_malformed_response_chunk() {
        //given
        wireMockServer.stubFor(WireMock
                                       .get("/offers")
                                       .willReturn(WireMock
                                                           .aResponse()
                                                           .withStatus(HttpStatus.SC_OK)
                                                           .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                                                           .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));
        //when
        Throwable throwable = catchThrowable(() -> offerResponseClient.fetchAllOfferFromForeignAPI());
        //then
        assertThat(throwable).isExactlyInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");

    }

    @Test
    void should_throw_exception_500_when_fault_random_data_then_close() {
        //given
        wireMockServer.stubFor(WireMock
                                       .get("/offers")
                                       .willReturn(WireMock
                                                           .aResponse()
                                                           .withStatus(HttpStatus.SC_OK)
                                                           .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                                                           .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));
        //when
        Throwable throwable = catchThrowable(() -> offerResponseClient.fetchAllOfferFromForeignAPI());
        //then
        assertThat(throwable).isExactlyInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    void should_throw_exception_500_when_status_is_204_no_content() {
        //given
        wireMockServer.stubFor(WireMock
                                       .get("/offers")
                                       .willReturn(WireMock.aResponse()
                                                           .withStatus(HttpStatus.SC_NO_CONTENT)
                                                           .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                                                           .withBody(bodyWithFourOffersJson())));
        //when
        Throwable throwable = catchThrowable(() -> offerResponseClient.fetchAllOfferFromForeignAPI());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("204 NO_CONTENT");
    }
}