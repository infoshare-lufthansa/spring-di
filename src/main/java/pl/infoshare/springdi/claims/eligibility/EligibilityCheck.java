package pl.infoshare.springdi.claims.eligibility;

import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimRegulation;

public interface EligibilityCheck {

    ClaimRegulation check(Claim claim);
}
