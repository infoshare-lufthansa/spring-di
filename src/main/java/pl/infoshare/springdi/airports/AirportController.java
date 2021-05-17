package pl.infoshare.springdi.airports;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.springdi.airports.model.Airport;
import pl.infoshare.springdi.airports.provider.AirportProvider;
import pl.infoshare.springdi.airports.provider.FileSource;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AirportController {

    private final AirportProvider airportProvider;

    @GetMapping("/api/airports/{iata}")
    public Optional<Airport> getAirport(@PathVariable String iata) {
        return airportProvider.getAirport(iata);
    }
}
