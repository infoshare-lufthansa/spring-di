package pl.infoshare.springdi.claims.eligibility.checks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.airports.provider.AirportProvider;
import pl.infoshare.springdi.claims.eligibility.EligibilityCheck;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimRegulation;

@Component
@RequiredArgsConstructor
public class AirportCheck implements EligibilityCheck {

    private final AirportProvider airportProvider;

    @Override
    public ClaimRegulation check(Claim claim) {
        var starting = airportProvider.getAirport(claim.getStartingAirport());
        var destination = airportProvider.getAirport(claim.getDestinationAirport());

        if (starting.isEmpty() || destination.isEmpty()) {
            return ClaimRegulation.NONE;
        }

        var startingContinent = starting.get().getContinent();
        var destinationContinent = destination.get().getContinent();
        if (startingContinent == Continent.EU || destinationContinent == Continent.EU) {
            return ClaimRegulation.EC_261;
        }
        if (startingContinent == Continent.SA && destinationContinent == Continent.SA) {
            return ClaimRegulation.BRAZIL;
        }

        return ClaimRegulation.NONE;
    }
}
