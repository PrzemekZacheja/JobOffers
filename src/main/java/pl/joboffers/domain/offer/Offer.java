package pl.joboffers.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public record Offer(
        @Id
        String id,
        String title,
        String company,
        String salary,
        @Indexed(unique = true) String offerUrl
) {
}