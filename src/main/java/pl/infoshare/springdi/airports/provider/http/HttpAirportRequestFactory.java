package pl.infoshare.springdi.airports.provider.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.configuration.properties.HttpSourceProperties;

import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpRequest;

import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

@Component
@RequiredArgsConstructor
public class HttpAirportRequestFactory {

    private static final String URI_PATTERN = "%s?iata=%s";

    private final ObjectMapper objectMapper;
    private final HttpSourceProperties httpSourceProperties;

    public HttpRequest createForIata(String iata) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(URI_PATTERN, httpSourceProperties.getUri(), iata)))
                .header("APC-Auth", httpSourceProperties.getApiKey())
                .header("APC-Auth-Secret", httpSourceProperties.getApiSecret())
                .POST(noBody())
                .build();
    }
}
