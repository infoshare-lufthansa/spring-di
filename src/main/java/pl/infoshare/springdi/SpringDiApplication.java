package pl.infoshare.springdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringDiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDiApplication.class, args);
    }

}
