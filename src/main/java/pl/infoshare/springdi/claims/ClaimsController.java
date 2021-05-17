package pl.infoshare.springdi.claims;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.springdi.claims.eligibility.ClaimEligibilityService;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimEligibility;

@RestController
@RequiredArgsConstructor
public class ClaimsController {

    private final ClaimEligibilityService claimEligibilityService;

    @PostMapping("/api/eligibility-check")
    public ClaimEligibility checkEligibility(@RequestBody Claim claim) {
        return claimEligibilityService.checkEligibility(claim);
    }
}
