package pl.infoshare.springdi.claims.model;

import lombok.Value;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
@Value
public class ClaimEligibility {
    boolean eligible;
    ClaimRegulation regulation;
}