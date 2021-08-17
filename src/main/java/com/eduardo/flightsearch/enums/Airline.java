package com.eduardo.flightsearch.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum Airline {

  IBERIA("IB", 10),
  BRITISH_AIRWAYS("BA", 1),
  LUFTHANSA("LH", 7),
  RYANAIR("FR", 20),
  VUELING("VY", 10),
  TURKISH_AIRLINES("TK", 5),
  EASYJET("U2", 19.9);

  private String code;
  private double infantPrice;
}