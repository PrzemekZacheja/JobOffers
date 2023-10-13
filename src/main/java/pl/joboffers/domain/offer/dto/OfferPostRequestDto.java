package pl.joboffers.domain.offer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record OfferPostRequestDto(
        @NotNull(message = "{offer.title.NotNull}")
        @NotEmpty(message = "{offer.title.NotEmpty}")
        String title,
        @NotNull(message = "{offer.company.NotNull}")
        @NotEmpty(message = "{offer.company.NotEmpty}")
        String company,
        @NotNull(message = "{offer.salary.NotNull}")
        @NotEmpty(message = "{offer.salary.NotEmpty}")
        String salary,
        @NotNull(message = "{offer.offerUrl.NotNull}")
        @NotEmpty(message = "{offer.offerUrl.NotEmpty}")
        String offerUrl
) {
}