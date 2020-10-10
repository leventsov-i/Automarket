package ru.vtb.marketplace;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.vtb.marketplace.pojo.Marketplace;

/**
 * @author denis-panin
 */
public class VtbApiClient {
    private static final URI API_URL = URI.create("https://gw.hackathon.vtb.ru/vtb/hackathon/");
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpClient client;
    private final String token;

    public VtbApiClient(@Value("@{vtb-api.key}") String token) {
        this.token = token;
        this.client = HttpClient.newHttpClient();
    }

    public Marketplace getMarketplace() throws IOException, InterruptedException {
        URI url = API_URL.resolve("marketplace");
        HttpRequest request = HttpRequest.newBuilder(url)
                .header("Accept", "application/json")
                .header("X-IBM-Client-Id", token)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error: " + response.body());
        }

        return mapper.readValue(response.body(), Marketplace.class);
    }
}
