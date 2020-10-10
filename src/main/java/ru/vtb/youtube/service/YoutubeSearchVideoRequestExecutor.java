package ru.vtb.youtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vtb.recognize.dto.RecognizeCarRequestVtbApiDto;
import ru.vtb.recognize.dto.RecognizeCarResponseVtbApiDto;
import ru.vtb.youtube.dto.exclude.BaseYoutubeResponseDto;

import java.net.URI;

@Component
public class YoutubeSearchVideoRequestExecutor {
    private final RestTemplate template;

    @Value("${youtube-api.host}")
    private String host;
    @Value("${youtube-api.endpoint.search}")
    private String endpointSearchVideos;
    @Value("${youtube-api.key}")
    private String youtubeApiKey;

    @Autowired
    public YoutubeSearchVideoRequestExecutor(RestTemplate template) {
        this.template = template;
    }

    public BaseYoutubeResponseDto execute(String searchString) {
        URI targetUri = createUri("обзор" + searchString.toLowerCase());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<BaseYoutubeResponseDto> entity = new HttpEntity<>(headers);


        ResponseEntity<BaseYoutubeResponseDto> response =
                template.exchange(targetUri, HttpMethod.GET, entity, BaseYoutubeResponseDto.class);

        return response.getBody();
    }

    private URI createUri(String stringSearch) {
        return UriComponentsBuilder.fromUriString(host)
                .path("/" + endpointSearchVideos)
                .queryParam("part", "snippet")
                .queryParam("key", youtubeApiKey)
                .queryParam("q", stringSearch)
                .queryParam("relevanceLanguage", "ru")
                .build()
                .encode()
                .toUri();
    }
}
