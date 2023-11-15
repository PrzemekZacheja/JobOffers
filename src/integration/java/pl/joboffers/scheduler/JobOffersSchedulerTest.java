package pl.joboffers.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.JobOffersSpringBootApplication;
import pl.joboffers.domain.offer.OfferResponseClient;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersSpringBootApplication.class, properties = "scheduling.enabled=true;")
class JobOffersSchedulerTest extends BaseIntegrationTest {

    @SpyBean
    private OfferResponseClient offerResponseClient;

    @Test
    void should_run_job_offers_get_method_exactly_given_times() {
        await().atMost(Duration.ofSeconds(3))
               .untilAsserted(() -> verify(offerResponseClient, times(3)).fetchAllUniqueOfferFromForeignAPI());
    }

}