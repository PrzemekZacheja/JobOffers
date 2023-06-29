package pl.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class OfferFacadeConfiguration {

    @Bean
    HashGenerator hashGenerator() {
        return new HashGenerator();
    }

    @Bean
    OfferFacade offerFacade(OfferResponseClient client, OfferFacadeRepository repository, HashGenerator hashGenerator) {
        return new OfferFacade(client, repository, hashGenerator);
    }
}