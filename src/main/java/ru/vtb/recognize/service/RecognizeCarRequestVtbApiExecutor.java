package ru.vtb.recognize.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vtb.recognize.dto.RecognizeCarRequestVtbApiDto;
import ru.vtb.recognize.dto.RecognizeCarResponseVtbApiDto;
import ru.vtb.recognize.exception.RecognizeCarVtbApiException;

import java.net.URI;

@Component
@Slf4j
public class RecognizeCarRequestVtbApiExecutor {
    public static final String X_IBM_CLIENT_ID = "X-IBM-Client-Id";
    private final RestTemplate template;

    @Value("${vtb-api.host}")
    private String host;
    @Value("${vtb-api.endpoint.car-recognize}")
    private String endpointRecognizeCar;
    @Value("${vtb-api.key}")
    private String vtbApiKey;


    @Autowired
    public RecognizeCarRequestVtbApiExecutor(RestTemplate template) {
        this.template = template;
    }

    public RecognizeCarResponseVtbApiDto execute(RecognizeCarRequestVtbApiDto requestBody) {
        URI uri = createUri();
        log.debug("Uri for request recognize car: {}", uri);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add(X_IBM_CLIENT_ID, vtbApiKey);
        HttpEntity<RecognizeCarRequestVtbApiDto> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<RecognizeCarResponseVtbApiDto> response =
                template.postForEntity(uri, entity, RecognizeCarResponseVtbApiDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new RecognizeCarVtbApiException("Incorrect base64 picture");
        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new RecognizeCarVtbApiException("UNAUTHORIZED");
        } else {
            log.error("Execute request failed. Response: {}", response);
            throw new RecognizeCarVtbApiException("Unknown problem");
        }
    }

    private URI createUri() {
        return UriComponentsBuilder.fromUriString(host)
                .path("/" + endpointRecognizeCar)
                .build()
                .encode()
                .toUri();
    }
}
