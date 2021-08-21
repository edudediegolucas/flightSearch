package es.edudediegolucas.flightsearch;

import es.edudediegolucas.flightsearch.services.ReadAndParseFile;
import es.edudediegolucas.flightsearch.services.SearchFlight;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
class SearchFlightTest {

  private SearchFlight searchFlight;
  private Map<String, Map<String, List<ReadAndParseFile.Flight>>> mapFlights;

  @BeforeEach
  void beforeEach() {
    searchFlight = new SearchFlight();
    mapFlights = new TreeMap<>();
  }

  @Test
  void testSearchFlight() {
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    putOriginDestinationToMapFlight(origin, destination);
    putRandomOriginDestinationToMapFlight();
    var flights = searchFlight.searchFlight(mapFlights, origin, destination);
    Assertions.assertNotNull(flights);
    Assertions.assertEquals(origin, flights.get(0).getOrigin());
    Assertions.assertEquals(destination, flights.get(0).getDestination());
  }

  @Test
  void testSearchFlightNullMap() {
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    Assertions.assertThrows(NullPointerException.class, () -> searchFlight.searchFlight(null, origin, destination));
  }

  @Test
  void testSearchFlightEmptyOrigin() {
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    putRandomOriginDestinationToMapFlight();
    Assertions.assertThrows(NullPointerException.class, () -> searchFlight.searchFlight(mapFlights, null, destination));
  }

  @Test
  void testSearchFlightEmptyDestination() {
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    putOriginDestinationToMapFlight(origin, RandomStringUtils.randomAlphabetic(3).toUpperCase());
    putRandomOriginDestinationToMapFlight();
    Assertions.assertThrows(NullPointerException.class, () -> searchFlight.searchFlight(mapFlights, origin, null));
  }

  @Test
  void testSearchFlightNotFoundOrigin() {
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    putRandomOriginDestinationToMapFlight();
    Assertions.assertThrows(NoSuchElementException.class, () -> searchFlight.searchFlight(mapFlights, origin, destination));
  }

  @Test
  void testSearchFlightNotFoundDestination() {
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    putOriginDestinationToMapFlight(origin, RandomStringUtils.randomAlphabetic(3).toUpperCase());
    putRandomOriginDestinationToMapFlight();
    Assertions.assertThrows(NoSuchElementException.class, () -> searchFlight.searchFlight(mapFlights, origin, destination));
  }

  private void putOriginDestinationToMapFlight(final String origin, final String destination) {
    mapFlights.put(origin, createMapDestination(origin, destination));
  }

  private void putRandomOriginDestinationToMapFlight() {
    IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 10))
            .forEach(ignored -> {
              final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
              final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
              mapFlights.put(origin, createMapDestination(origin, destination));
            });
  }

  private Map<String, List<ReadAndParseFile.Flight>> createMapDestination(final String origin, final String destination) {
    final var mapDestination = new TreeMap<String, List<ReadAndParseFile.Flight>>();
    IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 10))
            .forEach(ignored -> mapDestination.put(destination, createListOfFlight(origin, destination)));
    return mapDestination;
  }

  private List<ReadAndParseFile.Flight> createListOfFlight(final String origin, final String destination) {
    final var listFlight = new ArrayList<ReadAndParseFile.Flight>();
    IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 10))
            .forEach(ignored -> listFlight.add(ReadAndParseFile.Flight.builder()
                    .codeFlight(RandomStringUtils.randomAlphanumeric(6).toUpperCase())
                    .origin(origin)
                    .destination(destination)
                    .price(String.format("%.2f", ThreadLocalRandom.current().nextDouble(1.0, 1000.0)).concat(" €"))
                    .build()));
    return listFlight;
  }
}
