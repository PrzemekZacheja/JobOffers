package pl.joboffers.infrastracture.offer.http;

import lombok.Builder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.joboffers.domain.offer.OfferResponseClient;

import java.time.Duration;

@Configuration
@Builder
public class OfferResponseClientConfig {

    private final OfferResponseRestTemplateConfigurationProperties configuration;

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(configuration.connectionTimeout()))
                .setReadTimeout(Duration.ofMillis(configuration.readTimeout()))
                .build();
    }

    @Bean
    public OfferResponseClient remoteJobOfferClient(RestTemplate restTemplate) {
        return new OfferResponseClientRestTemplate(restTemplate, configuration.uri(), configuration.port());
    }
}