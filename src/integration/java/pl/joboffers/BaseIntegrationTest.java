package pl.joboffers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.joboffers.domain.JobOffersSpringBootApplication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = JobOffersSpringBootApplication.class)
class BaseIntegrationTest {

    @Test
    void testJobOffersSpringBootApplication() {
        assertThat(1).isEqualTo(1);
    }
}