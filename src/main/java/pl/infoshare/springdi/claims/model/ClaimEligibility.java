package pl.infoshare.springdi.claims.model;

import lombok.Value;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
@Value
public class ClaimEligibility {
    boolean eligible;
    ClaimRegulation regulation;

    public static ClaimEligibility ineligibile() {
        return new ClaimEligibility(false, null);
    }

    public static ClaimEligibility forRegulation(ClaimRegulation regulation) {
        if (regulation == ClaimRegulation.NONE) {
            return ineligibile();
        }
        return new ClaimEligibility(true, regulation);
    }
}