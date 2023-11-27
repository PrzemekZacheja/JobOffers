package pl.joboffers.http.error;

import org.springframework.web.client.RestTemplate;
import pl.joboffers.domain.offer.OfferResponseClient;
import pl.joboffers.infrastracture.offer.http.OfferResponseClientConfig;
import pl.joboffers.infrastracture.offer.http.OfferResponseClientRestTemplate;

public class OfferResponseClientRestTemplateErrorsIntegrationTestConfig extends OfferResponseClientConfig {

    public OfferResponseClient remoteJobOfferClient(int port, int connectionTimeout, int readTimeout) {
        RestTemplate restTemplate = restTemplate(restTemplateResponseErrorHandler(), connectionTimeout, readTimeout);
        return new OfferResponseClientRestTemplate(restTemplate, "http://localhost", port);
    }
}