package pl.infoshare.springdi.airports;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.springdi.airports.model.Airport;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AirportController {

    @GetMapping("/api/airports/{iata}")
    public Optional<Airport> getAirport(@PathVariable String iata) {
        return Optional.empty();
    }
}
