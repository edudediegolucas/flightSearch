package es.edudediegolucas.flightsearch;

import es.edudediegolucas.flightsearch.controllers.FlightSearchController;
import es.edudediegolucas.flightsearch.enums.Airport;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FlightSearchControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @Test
  void testSpringLoads() {
    Assertions.assertTrue(Boolean.TRUE);
  }

  @SneakyThrows
  @Test
  void testAllFlightsRaw() {
    mockMvc.perform(get("/flightsearch/api/v1/getallflights"))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void testSearchFlight() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", Airport.MADRID.getCode())
                    .param("destination", Airport.BARCELONA.getCode()))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void testSearchFlightInvalidOriginCity() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", "XXX")
                    .param("destination", Airport.BARCELONA.getCode()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightInvalidDestinationCity() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", Airport.MADRID.getCode())
                    .param("destination", "XXX"))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightWrongParam() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("asasa", Airport.MADRID.getCode()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightMissingOriginValue() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", StringUtils.EMPTY)
                    .param("destination", Airport.BARCELONA.getCode()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightMissingOriginValueAgain() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", StringUtils.SPACE)
                    .param("destination", Airport.BARCELONA.getCode()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightMissingDestinationValue() {
    mockMvc.perform(get(("/flightsearch/api/v1/getflights"))
                    .param("origin", Airport.MADRID.getCode())
                    .param("destination", StringUtils.EMPTY))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testSearchFlightMissingDestinationValueAgain() {
    mockMvc.perform(get("/flightsearch/api/v1/getflights")
                    .param("origin", Airport.MADRID.getCode())
                    .param("destination", StringUtils.SPACE))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPrice() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(1)
                            .children(0)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongDataDays() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(0)
                            .adults(1)
                            .children(0)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongAdultsNumber() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(0)
                            .children(0)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongOrigin() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(StringUtils.EMPTY)
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(0)
                            .children(0)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongDestination() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(StringUtils.EMPTY)
                            .days(1)
                            .adults(1)
                            .children(0)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongChildrenNumber() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(1)
                            .children(-1)
                            .infants(0)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceInfantsNumber() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(1)
                            .children(0)
                            .infants(1)
                            .build())))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void testCalculateFlightWithPriceWrongInfantsNumber() {
    mockMvc.perform(post("/flightsearch/api/v1/flights")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(gson.toJson(FlightSearchController.RequestFlightCriteria.builder()
                            .origin(Airport.MADRID.getCode())
                            .destination(Airport.BARCELONA.getCode())
                            .days(1)
                            .adults(1)
                            .children(0)
                            .infants(-1)
                            .build())))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}
