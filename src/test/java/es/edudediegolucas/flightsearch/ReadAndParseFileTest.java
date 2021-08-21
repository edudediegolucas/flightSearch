package es.edudediegolucas.flightsearch;

import es.edudediegolucas.flightsearch.enums.Airport;
import es.edudediegolucas.flightsearch.services.ReadAndParseFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

class ReadAndParseFileTest {

  private ReadAndParseFile readAndParseFile;

  @Test
  void testInitReadingAndParsingFile() {
    readAndParseFile = new ReadAndParseFile(Path.of("src/main/resources/flights.csv"));
    readAndParseFile.initReadingAndParsingFile();
    Assertions.assertNotNull(readAndParseFile.getFlightsMap());
    Assertions.assertEquals(9, readAndParseFile.getFlightsMap().size());
    Assertions.assertEquals(6, readAndParseFile.getFlightsMap().get(Airport.MADRID.getCode()).size());
    Assertions.assertEquals(3, readAndParseFile.getFlightsMap().get(Airport.MADRID.getCode()).get(Airport.PARIS.getCode()).size());
  }

  @Test
  void testInitReadingAndParsingFileError() {
    readAndParseFile = new ReadAndParseFile(Path.of("src/main/resources/foo.csv"));
    Assertions.assertThrows(NoSuchFileException.class, () -> readAndParseFile.initReadingAndParsingFile());
  }
}
