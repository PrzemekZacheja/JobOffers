package pl.joboffers.infrastracture.offer.http;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "joboffers.offer.http.client.config")
@Builder
public record OfferResponseRestTemplateConfigurationProperties(long connectionTimeout, long readTimeout, String uri,
                                                               int port) {
}