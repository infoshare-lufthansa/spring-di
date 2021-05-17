package pl.infoshare.springdi.airports.provider.http;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Airport;
import pl.infoshare.springdi.airports.model.HttpAirportResponse;
import pl.infoshare.springdi.airports.provider.AirportProvider;
import pl.infoshare.springdi.airports.provider.HttpSource;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Optional;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

@Component
@HttpSource
@RequiredArgsConstructor
public class HttpAirportProvider implements AirportProvider {

    private final HttpClient httpClient;
    private final HttpAirportRequestFactory httpAirportRequestFactory;

    @Override
    public Optional<Airport> getAirport(String iata) {
        var request = httpAirportRequestFactory.createForIata(iata);
        var response =  sendRequest(request);
        if (response.isStatus()) {
            var airport = response.getAirport();
            return Optional.of(new Airport(iata, airport.getName(), airport.getContinent().getAbbr()));
        }

        return Optional.empty();
    }

    private HttpAirportResponse sendRequest(HttpRequest request) {
        try {
            var body = httpClient.send(request, ofString()).body();
            return HttpAirportResponse.fromResponse(body);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
