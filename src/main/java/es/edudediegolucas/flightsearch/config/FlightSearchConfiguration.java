package es.edudediegolucas.flightsearch.config;

import es.edudediegolucas.flightsearch.services.CalculateFlight;
import es.edudediegolucas.flightsearch.services.ReadAndParseFile;
import es.edudediegolucas.flightsearch.services.SearchFlight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class FlightSearchConfiguration {

  @Bean
  ReadAndParseFile readFile() {
    return new ReadAndParseFile(Path.of("src/main/resources/flights.csv"));
  }

  @Bean
  SearchFlight searchFlight() {
    return new SearchFlight();
  }

  @Bean
  CalculateFlight calculateFlight() {
    return new CalculateFlight();
  }
}
