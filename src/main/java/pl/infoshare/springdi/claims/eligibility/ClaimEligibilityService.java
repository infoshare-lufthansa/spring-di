package pl.infoshare.springdi.claims.eligibility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.provider.AirportProvider;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimEligibility;

import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class ClaimEligibilityService {

    private final AirportProvider airportProvider;
    private final List<EligibilityCheck> eligibilityChecks;

    public ClaimEligibility checkEligibility(Claim claim) {
        if (airportProvider.getAirport(claim.getStartingAirport()).isEmpty()) {
            return ClaimEligibility.ineligibile();
        }
        if (airportProvider.getAirport(claim.getDestinationAirport()).isEmpty()) {
            return ClaimEligibility.ineligibile();
        }

        return eligibilityChecks.stream()
                .map(e -> e.check(claim))
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .map(Map.Entry::getKey)
                .map(ClaimEligibility::forRegulation)
                .orElse(ClaimEligibility.ineligibile());
    }
}
