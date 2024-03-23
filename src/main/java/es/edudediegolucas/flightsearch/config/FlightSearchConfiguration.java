package es.edudediegolucas.flightsearch.config;

import es.edudediegolucas.flightsearch.services.ReadAndParseFile;
import java.nio.file.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightSearchConfiguration {

  @Bean
  ReadAndParseFile readFile() {
    return new ReadAndParseFile(Path.of("src/main/resources/flights.csv"));
  }
}
