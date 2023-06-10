package pl.joboffers.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration public class OfferFacadeConfiguration {

    @Bean
    OfferFacadeRepository offerFacadeRepository() {
        return new OfferFacadeRepository() {
            @Override
            public OfferResponseObject save(final OfferResponseObject offer) {
                return null;
            }

            @Override
            public OfferResponseObject findByLinkAsId(final String link) {
                return null;
            }

            @Override
            public List<OfferResponseObject> getAllOffersFromRepository() {
                return Collections.emptyList();
            }

            @Override
            public OfferResponseObject findOfferById(final String id) {
                return null;
            }
        };
    }

    @Bean
    HashGenerator hashGenerator() {
        return new HashGenerator();
    }

    @Bean
    OfferFacade offerFacade(OfferFacadeRepository repository, OfferResponseClient client, HashGenerator hashGenerator) {
        return new OfferFacade(client, repository, hashGenerator);
    }
}