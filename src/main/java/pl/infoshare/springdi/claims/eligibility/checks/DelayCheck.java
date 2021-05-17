package pl.infoshare.springdi.claims.eligibility.checks;

import org.springframework.stereotype.Component;
import pl.infoshare.springdi.claims.eligibility.EligibilityCheck;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimRegulation;

import java.math.BigDecimal;

@Component
public class DelayCheck implements EligibilityCheck {

    @Override
    public ClaimRegulation check(Claim claim) {
        if (claim.getTotalDelayInHours().compareTo(BigDecimal.valueOf(4)) > 0) {
            return ClaimRegulation.BRAZIL;
        }
        if (claim.getTotalDelayInHours().compareTo(BigDecimal.valueOf(3)) > 0) {
            return ClaimRegulation.EC_261;
        }

        return ClaimRegulation.NONE;
    }
}
