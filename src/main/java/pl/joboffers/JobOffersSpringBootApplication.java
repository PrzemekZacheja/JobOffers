package pl.joboffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.joboffers.infrastracture.offer.http.OfferResponseRestTemplateConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OfferResponseRestTemplateConfigurationProperties.class})
public class JobOffersSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobOffersSpringBootApplication.class, args);
    }
}