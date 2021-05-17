package pl.infoshare.springdi.claims.eligibility.checks;

import org.springframework.stereotype.Component;
import pl.infoshare.springdi.claims.eligibility.EligibilityCheck;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimRegulation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateCheck implements EligibilityCheck {

    @Override
    public ClaimRegulation check(Claim claim) {
        var yearsBefore = ChronoUnit.YEARS.between(claim.getFlightDate(), LocalDate.now());
        if (claim.isCancelled() && yearsBefore < 2) {
            return ClaimRegulation.EC_261;
        }
        if (yearsBefore < 3) {
            return ClaimRegulation.EC_261;
        }
        if (yearsBefore < 4) {
            return ClaimRegulation.BRAZIL;
        }

        return ClaimRegulation.NONE;
    }
}
