package pl.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferFacadeConfiguration {


    @Bean
    OfferFacade offerFacade(OfferResponseClient client, OfferFacadeRepository repository) {
        return new OfferFacade(client, repository);
    }
}