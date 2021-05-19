package pl.infoshare.springdi.airports.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.UncheckedIOException;

/**
 * Uwaga: Potrzebne dopiero do zadania trzeciego.
 * Model reprezentujący odpowiedź z serwisu pozwalającego na wyszukiwanie lotnisk na podstawie ich kodu IATA. Do konwersji z JSONa na obiekt wykorzystuje bibliotekę Jackson.
 */
@Value
public class HttpAirportResponse {
    /**
     * Określa czy udało się wyszukać lotnisko czy nie.
     */
    boolean status;
    /**
     * Zawiera podstawowe informacje o lotnisku
     */
    Airport airport;

    /**
     * Statyczna faktorka konwertujący JSON Body otrzymane jako String w pełnoprawny obiekt reprezentujący odpowiedź
     * z serwisu
     * @param body JSON z odpowiedzią
     * @return skonwertowany z JSONa obiekt
     */
    public static HttpAirportResponse fromResponse(String body) {
        var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(body, HttpAirportResponse.class);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    @JsonCreator
    public HttpAirportResponse(@JsonProperty("status") boolean status,
                               @JsonProperty("airport") Airport airport) {
        this.status = status;
        this.airport = airport;
    }

    @Value
    public static class Airport {
        String name;
        AirportContinent continent;

        @JsonCreator
        public Airport(@JsonProperty("name") String name,
                       @JsonProperty("continent") AirportContinent continent) {
            this.name = name;
            this.continent = continent;
        }
    }

    @Value
    public static class AirportContinent {
        Continent abbr;

        @JsonCreator
        public AirportContinent(@JsonProperty("abbr") Continent abbr) {
            this.abbr = abbr;
        }
    }
}