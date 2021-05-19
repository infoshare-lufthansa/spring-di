package pl.infoshare.springdi.claims.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
@Value
public class Claim {
    private final static BigDecimal MINUTES_IN_HOUR = BigDecimal.valueOf(60);

    ClaimType type;
    Integer totalDelay;
    String startingAirport;
    String destinationAirport;
    LocalDate flightDate;

    @JsonCreator
    public Claim(@JsonProperty("type") ClaimType type,
                 @JsonProperty("totalDelay") Integer totalDelay,
                 @JsonProperty("startingAirport") String startingAirport,
                 @JsonProperty("destinationAirport") String destinationAirport,
                 @JsonProperty("flightDate") LocalDate flightDate) {
        this.type = type;
        this.totalDelay = totalDelay;
        this.startingAirport = startingAirport;
        this.destinationAirport = destinationAirport;
        this.flightDate = flightDate;
    }

    public BigDecimal getTotalDelayInHours() {
        return BigDecimal.valueOf(totalDelay).divide(MINUTES_IN_HOUR, RoundingMode.HALF_UP);
    }

    public boolean isCancelled() {
        return type == ClaimType.CANCELLED;
    }

    public boolean isDelayed() {
        return type == ClaimType.DELAYED;
    }
}

