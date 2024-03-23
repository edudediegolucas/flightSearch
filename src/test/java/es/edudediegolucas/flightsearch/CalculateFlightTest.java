package es.edudediegolucas.flightsearch;

import es.edudediegolucas.flightsearch.enums.Airline;
import es.edudediegolucas.flightsearch.services.CalculateFlight;
import es.edudediegolucas.flightsearch.services.ReadAndParseFile;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculateFlightTest {

  private CalculateFlight calculateFlight;
  private List<ReadAndParseFile.Flight> flightList;

  @BeforeEach
  void beforeEach() {
    calculateFlight = new CalculateFlight();
  }

  @Test
  void testCalculateFlight() {
    flightList = createListFlights();
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightRandomAdults() {
    flightList = createListFlights();
    final int randomAdults = ThreadLocalRandom.current().nextInt(1, 1000);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, randomAdults, 0, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithChildren() {
    flightList = createListFlights();
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 1, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightRandomChildren() {
    flightList = createListFlights();
    final int randomChildren = ThreadLocalRandom.current().nextInt(1, 1000);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, randomChildren, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsBritishAirways() {
    addExistingAirLine(Airline.BRITISH_AIRWAYS);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsEasyJet() {
    addExistingAirLine(Airline.EASYJET);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsIberia() {
    addExistingAirLine(Airline.IBERIA);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsLufthansa() {
    addExistingAirLine(Airline.LUFTHANSA);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsRyanair() {
    addExistingAirLine(Airline.RYANAIR);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsTurkishAirlines() {
    addExistingAirLine(Airline.TURKISH_AIRLINES);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsVueling() {
    addExistingAirLine(Airline.VUELING);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightMoreThan30days() {
    flightList = createListFlights();
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 31, 1, 0, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightMoreThan16days() {
    flightList = createListFlights();
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 17, 1, 0, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightMoreThan3days() {
    flightList = createListFlights();
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, 4, 1, 0, 0);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightRandomAll() {
    addExistingAirLine(Airline.BRITISH_AIRWAYS);
    addExistingAirLine(Airline.EASYJET);
    addExistingAirLine(Airline.IBERIA);
    addExistingAirLine(Airline.LUFTHANSA);
    addExistingAirLine(Airline.RYANAIR);
    addExistingAirLine(Airline.TURKISH_AIRLINES);
    addExistingAirLine(Airline.VUELING);
    final int days = ThreadLocalRandom.current().nextInt(1, 1000);
    final int randomAdults = ThreadLocalRandom.current().nextInt(1, 1000);
    final int randomChildren = ThreadLocalRandom.current().nextInt(1, 1000);
    final int randomInfants = ThreadLocalRandom.current().nextInt(1, 1000);
    var flightResults = calculateFlight.calculatePriceForFlight(flightList, days, randomAdults, randomChildren, randomInfants);
    Assertions.assertNotNull(flightResults);
    Assertions.assertEquals(flightList.size(), flightResults.size());
  }

  @Test
  void testCalculateFlightWithInfantsNoAirline() {
    flightList = createListFlights();
    Assertions.assertThrows(NoSuchElementException.class, () -> calculateFlight.calculatePriceForFlight(flightList, 1, 1, 0, 1));
  }

  private void addExistingAirLine(Airline airline) {
    flightList = new ArrayList<>();
    flightList.add(ReadAndParseFile.Flight.builder()
        .codeFlight(airline.getCode())
        .origin(RandomStringUtils.randomAlphabetic(3).toUpperCase())
        .destination(RandomStringUtils.randomAlphabetic(3).toUpperCase())
        .price(String.format("%.2f", ThreadLocalRandom.current().nextDouble(1.0, 1000.0)).concat(" €"))
        .build());
  }

  private List<ReadAndParseFile.Flight> createListFlights() {
    flightList = new ArrayList<>();
    final String origin = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    final String destination = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 10))
        .forEach(ignored -> flightList.add(ReadAndParseFile.Flight.builder()
            .codeFlight(RandomStringUtils.randomAlphanumeric(6).toUpperCase())
            .origin(origin)
            .destination(destination)
            .price(String.format("%.2f", ThreadLocalRandom.current().nextDouble(1.0, 1000.0)).concat(" €"))
            .build()));
    return flightList;
  }
}
