package pl.joboffers.infrastracture.offer.http;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joboffers.domain.offer.OfferResponseClient;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

@AllArgsConstructor
public class OfferResponseClientRestTemplate implements OfferResponseClient {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int ports;

    @Override
    public List<OfferGetResponseDto> fetchAllOfferFromForeignAPI() {
        String url = UriComponentsBuilder.fromHttpUrl(getUrlForService())
                                         .toUriString();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<OfferGetResponseDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return responseEntity.getBody();
    }

    private String getUrlForService() {
        return uri + ":" + ports + "/offers";
    }

}