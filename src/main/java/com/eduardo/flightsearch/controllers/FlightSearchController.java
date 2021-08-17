package com.eduardo.flightsearch.controllers;

import com.eduardo.flightsearch.services.CalculateFlight;
import com.eduardo.flightsearch.services.ReadAndParseFile;
import com.eduardo.flightsearch.services.SearchFlight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("flightsearch/api/v1")
public class FlightSearchController {

  private final ReadAndParseFile readAndParseFile;
  private final SearchFlight searchFlight;
  private final CalculateFlight calculateFlight;

  @GetMapping(value = "/getallflights", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Map<String, List<ReadAndParseFile.Flight>>> getAllFlights() {
    log.info("called /getallflights");
    return readAndParseFile.getFlightsMap();
  }

  @GetMapping(value = "/getflights", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ReadAndParseFile.Flight> getListOfFlights(@RequestParam String origin,
                                                        @RequestParam String destination) {
    log.info("called /getflights with [{}] - > [{}]", origin, destination);
    try {
      return searchFlight.searchFlight(readAndParseFile.getFlightsMap(), StringUtils.trimToEmpty(origin), StringUtils.trimToEmpty(destination));
    } catch (NoSuchElementException noSuchElementException) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/flights", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Set<CalculateFlight.FlightResult> calculateFlightsWithPrice(@RequestBody @Valid RequestFlightCriteria requestFlightCriteria) {
    log.info("called /flights with {}", requestFlightCriteria);
    return calculateFlight.calculatePriceForFlight(searchFlight.searchFlight(
                    readAndParseFile.getFlightsMap(),
                    StringUtils.trimToEmpty(requestFlightCriteria.getOrigin()),
                    StringUtils.trimToEmpty(requestFlightCriteria.getDestination())),
            requestFlightCriteria.getDays(),
            requestFlightCriteria.getAdults(),
            requestFlightCriteria.getChildren(),
            requestFlightCriteria.getInfants());
  }

  @Builder
  @AllArgsConstructor
  @Getter
  @ToString
  public static class RequestFlightCriteria {
    @NotEmpty(message = "Cannot be empty")
    private String origin;
    @NotEmpty(message = "Cannot be empty")
    private String destination;
    @Positive(message = "Days must be greater than 0")
    private int days;
    @Positive(message = "Adults must be greater than 0")
    private int adults;
    @Min(0)
    private int children;
    @Min(0)
    private int infants;
  }
}
