package pl.joboffers.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record OfferResponse(@Id String id,
                     @Indexed(unique = true) String linkToOffer,
                     String nameOfPosition,
                     String nameOfCompany,
                     String salary) {
}