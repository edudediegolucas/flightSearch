package es.edudediegolucas.flightsearch.services;

import es.edudediegolucas.flightsearch.enums.Airline;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CalculateFlight {

  private static final double PLUS_30_DAYS = 0.8;
  private static final double EQUALS_30_TO_16_DAYS = 1.0;
  private static final double EQUALS_15_TO_3_DAYS = 1.2;
  private static final double LESS_3_DAYS = 1.5;
  private static final double CHILD_BASE_PRICE = 0.2;

  public Set<FlightResult> calculatePriceForFlight(List<ReadAndParseFile.Flight> flightList,
      final int days,
      final int adults,
      final int children,
      final int infants) {
    var flightResultSet = new TreeSet<FlightResult>();
    final double basePercentage = getBasePercentage(days);
    flightList.forEach(flight -> {
      double totalPrice = adults * Double.parseDouble(StringUtils.trim(flight.getPrice()
          .replace("€", "")
          .replace(",", "."))) * basePercentage;
      if (children > 0) {
        totalPrice += children * basePercentage * CHILD_BASE_PRICE;
      }
      if (infants > 0) {
        totalPrice += getAirlineFromCode(flight.getCodeFlight().substring(0, 2)).getInfantPrice();
      }
      var flightResult = FlightResult.builder()
          .flightCode(flight.getCodeFlight())
          .origin(flight.getOrigin())
          .destination(flight.getDestination())
          .days(days)
          .adults(adults)
          .children(children)
          .infants(infants)
          .totalPrice(NumberFormat.getCurrencyInstance(Locale.ITALY).format(totalPrice))
          .build();
      flightResultSet.add(flightResult);
    });
    return flightResultSet;
  }

  private double getBasePercentage(final int days) {
    if (days > 30) {
      return PLUS_30_DAYS;
    }
    if (days >= 16) {
      return EQUALS_30_TO_16_DAYS;
    }
    if (days >= 3) {
      return EQUALS_15_TO_3_DAYS;
    }
    return LESS_3_DAYS;
  }

  private Airline getAirlineFromCode(final String code) {
    if (Airline.BRITISH_AIRWAYS.getCode().equals(code)) {
      return Airline.BRITISH_AIRWAYS;
    }
    if (Airline.EASYJET.getCode().equals(code)) {
      return Airline.EASYJET;
    }
    if (Airline.IBERIA.getCode().equals(code)) {
      return Airline.IBERIA;
    }
    if (Airline.LUFTHANSA.getCode().equals(code)) {
      return Airline.LUFTHANSA;
    }
    if (Airline.RYANAIR.getCode().equals(code)) {
      return Airline.RYANAIR;
    }
    if (Airline.TURKISH_AIRLINES.getCode().equals(code)) {
      return Airline.TURKISH_AIRLINES;
    }
    if (Airline.VUELING.getCode().equals(code)) {
      return Airline.VUELING;
    }
    throw new NoSuchElementException("This [Airline] does not exist!");
  }

  @Builder
  @AllArgsConstructor
  @ToString
  @Getter
  @EqualsAndHashCode
  public static class FlightResult implements Comparable<FlightResult> {
    private String flightCode;
    private String totalPrice;
    private String origin;
    private String destination;
    private int days;
    private int adults;
    private int children;
    private int infants;

    @Override
    public int compareTo(FlightResult o) {
      return this.flightCode.compareTo(o.getFlightCode());
    }
  }
}
