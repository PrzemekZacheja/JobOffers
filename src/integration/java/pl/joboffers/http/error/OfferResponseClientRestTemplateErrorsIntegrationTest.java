package pl.joboffers.http.error;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pl.joboffers.domain.offer.OfferResponseClient;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OfferResponseClientRestTemplateErrorsIntegrationTest {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    OfferResponseClient offerResponseClient = new OfferResponseClientRestTemplateErrorsIntegrationTestConfig().remoteJobOfferClient(
            wireMockServer.getPort(),
            1000,
            1000);

    @Test
    void should_return_null_when_fault_connection_reset_by_peer() {
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));
        //when
        List<OfferGetResponseDto> offerGetResponseDtos = offerResponseClient.fetchAllOfferFromForeignAPI();
        //then
        assertThat(offerGetResponseDtos).isEqualTo(null);
    }
}