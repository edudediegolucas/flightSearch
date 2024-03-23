package es.edudediegolucas.flightsearch.services;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SearchFlight {

  public List<ReadAndParseFile.Flight> searchFlight(
      @NotNull Map<String, Map<String, List<ReadAndParseFile.Flight>>> flightsMap,
      @NotEmpty String origin,
      @NotEmpty String destination) {
    var destinationMap = Optional.ofNullable(flightsMap.get(origin))
        .orElseThrow(() -> new NoSuchElementException("No [origin] airport found!"));
    return Optional.ofNullable(destinationMap.get(destination))
        .orElseThrow(() -> new NoSuchElementException("No [destination] airport found!"));
  }
}
