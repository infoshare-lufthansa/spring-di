package pl.infoshare.springdi.configuration.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.file.Path;

@Value
@ConstructorBinding
@ConfigurationProperties("airports.file")
public class FileSourceProperties {
    Path path;
}
