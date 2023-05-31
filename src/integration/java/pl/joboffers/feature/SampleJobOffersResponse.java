package pl.joboffers.feature;

public interface SampleJobOffersResponse {

    default String bodyWithZeroOffersJson() {
        return "[]";
    }
}