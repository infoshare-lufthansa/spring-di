package pl.infoshare.springdi.configuration.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.net.URI;

@Value
@ConstructorBinding
@ConfigurationProperties("airports.http")
public class HttpSourceProperties {
    String uri;
    String apiKey;
    String apiSecret;
}
