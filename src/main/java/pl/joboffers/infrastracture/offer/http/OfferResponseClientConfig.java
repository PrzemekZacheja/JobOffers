package pl.joboffers.infrastracture.offer.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.joboffers.domain.offer.OfferResponseClient;

import java.time.Duration;

@Configuration
public class OfferResponseClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public OfferResponseClient remoteJobOfferClient(RestTemplate restTemplate,
                                                    @Value("${joboffers.offer.http.client.config.uri}") String uri,
                                                    @Value("${joboffers.offer.http.client.config.port}") int port) {
        return new OfferResponseClientRestTemplate(restTemplate, uri, port);
    }
}