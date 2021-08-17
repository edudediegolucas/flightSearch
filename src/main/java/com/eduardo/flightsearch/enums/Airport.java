package com.eduardo.flightsearch.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum Airport {

  MADRID("MAD"),
  BARCELONA("BCN"),
  LONDON("LHR"),
  PARIS("CDG"),
  FRANKFURT("FRA"),
  ISTANBUL("IST"),
  AMSTERDAM("AMS"),
  ROME("FCO"),
  COPENHAGEN("CPH");

  private String code;
}
