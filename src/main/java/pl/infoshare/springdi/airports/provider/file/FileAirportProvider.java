package pl.infoshare.springdi.airports.provider.file;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Airport;
import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.airports.provider.AirportProvider;
import pl.infoshare.springdi.airports.provider.FileSource;
import pl.infoshare.springdi.configuration.properties.FileSourceProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class FileAirportProvider implements AirportProvider {

    public static final Path AIRPORTS_PATH = Paths.get("airports.csv");

    private final Map<String, Airport> airports = new HashMap<>();

    @PostConstruct
    void init() throws IOException {
        Files.readAllLines(AIRPORTS_PATH)
                .stream()
                .map(line -> line.split(","))
                .map(line -> new Airport(line[0], line[1], Continent.valueOf(line[2])))
                .forEach(airport -> airports.put(airport.getIata(), airport));
    }

    @Override
    public Optional<Airport> getAirport(String iata) {
        return Optional.ofNullable(airports.get(iata));
    }
}
