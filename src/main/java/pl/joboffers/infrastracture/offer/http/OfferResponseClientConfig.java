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
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler,
                                     @Value("${joboffers.offer.http.client.config.connectionTimeout:1000}") int connectionTimeout,
                                     @Value("${joboffers.offer.http.client.config.readTimeout:1000}") int readTimeout) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public OfferResponseClient remoteJobOfferClient(RestTemplate restTemplate,
                                                    @Value("${joboffers.offer.http.client.config.uri}") String uri,
                                                    @Value("${joboffers.offer.http.client.config.port}") int port) {
        return new OfferResponseClientRestTemplate(restTemplate, uri, port);
    }
}