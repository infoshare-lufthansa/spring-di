package pl.infoshare.springdi.airports.provider;

import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Airport;

import java.util.Optional;

public interface AirportProvider {

    Optional<Airport> getAirport(String iata);

}
