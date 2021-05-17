package pl.infoshare.springdi.airports.model;

import lombok.Value;

@Value
public class Airport {
    String iata;
    String name;
    Continent continent;
}
