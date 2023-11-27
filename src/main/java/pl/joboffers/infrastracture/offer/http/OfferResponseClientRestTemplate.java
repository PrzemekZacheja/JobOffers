package pl.joboffers.infrastracture.offer.http;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joboffers.domain.offer.OfferResponseClient;
import pl.joboffers.domain.offer.dto.OfferGetResponseDto;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class OfferResponseClientRestTemplate implements OfferResponseClient {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int ports;

    @Override
    public List<OfferGetResponseDto> fetchAllOfferFromForeignAPI() {
        String url = UriComponentsBuilder.fromHttpUrl(getUrlForService()).toUriString();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<OfferGetResponseDto>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            List<OfferGetResponseDto> body = responseEntity.getBody();
            if (body == null) {
                log.error("Response body is null");
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
            return body;
        } catch (ResourceAccessException resourceAccessException) {
            log.error("Error while fetching offers using http client");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUrlForService() {
        return uri + ":" + ports + "/offers";
    }

}