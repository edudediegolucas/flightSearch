package com.eduardo.flightsearch.config;

import com.eduardo.flightsearch.services.CalculateFlight;
import com.eduardo.flightsearch.services.ReadAndParseFile;
import com.eduardo.flightsearch.services.SearchFlight;
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
