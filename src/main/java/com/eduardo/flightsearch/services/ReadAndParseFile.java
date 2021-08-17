package com.eduardo.flightsearch.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@RequiredArgsConstructor
public class ReadAndParseFile {

  private final Path pathToCsv;
  @Getter
  private Map<String, Map<String, List<Flight>>> flightsMap;

  @SneakyThrows
  @PostConstruct
  public void initReadingAndParsingFile() {
    flightsMap = new TreeMap<>();
    try (var linesCsvFile = Files.lines(pathToCsv)) {
      linesCsvFile.forEach(line -> {
        String[] flightAttributes = line.split(",");
        var flight = Flight.builder()
                .origin(flightAttributes[0])
                .destination(flightAttributes[1])
                .codeFlight(flightAttributes[2])
                .price(flightAttributes[3])
                .build();
        // does origin exist?
        var flightMapDestinations = Optional.ofNullable(flightsMap.get(flight.getOrigin()));
        if (flightMapDestinations.isPresent()) {
          // does destination exist?
          var listOfDestinationFlightsFromMap = Optional.ofNullable(flightMapDestinations.get().get(flight.getDestination()));
          if (listOfDestinationFlightsFromMap.isPresent()) {
            listOfDestinationFlightsFromMap.get().add(flight);
          } else {
            var listOfDestinationFlights = new ArrayList<Flight>();
            listOfDestinationFlights.add(flight);
            flightMapDestinations.get().put(flight.getDestination(), listOfDestinationFlights);
          }
        } else {
          var listOfDestinationFlights = new ArrayList<Flight>();
          listOfDestinationFlights.add(flight);
          var destinationMap = new TreeMap<String, List<Flight>>();
          destinationMap.put(flight.getDestination(), listOfDestinationFlights);
          flightsMap.put(flight.getOrigin(), destinationMap);
        }
      });
    }
  }

  @Builder
  @Getter
  @ToString
  @AllArgsConstructor
  public static class Flight {
    private String origin;
    private String destination;
    private String codeFlight;
    private String price;
  }
}
