package com.eduardo.flightsearch.utils;

import com.eduardo.flightsearch.beans.ResultFlight;
import com.eduardo.flightsearch.constants.Airlines;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>This class has the logic for calculating the flights price.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class CalculatePriceFlight {

	//Constants for base price percentage

	private static final double PLUS_30_DAYS = 0.8;
	private static final double EQUALS_30_TO_16_DAYS = 1.0;
	private static final double EQUALS_15_TO_3_DAYS = 1.2;
	private static final double LESS_3_DAYS = 1.5;
	private static final double CHILD_BASE_PRICE = 0.2;

	/**
	 * Method that allows to multiply to the base price as a rule.
	 *
	 * @param days
	 * @return the percentage to multiply
	 */
	private double getBasePercentage(int days) {
		if (days > 30) {
			return PLUS_30_DAYS;
		}
		if ((days <= 30) && (days >= 16)) {
			return EQUALS_30_TO_16_DAYS;
		}
		if ((days <= 15) && (days >= 3)) {
			return EQUALS_15_TO_3_DAYS;
		}
		return LESS_3_DAYS;
	}

	/**
	 * If the flight has infants in it, we have to look for the fixed infant price for each airline.
	 *
	 * @param airlineCode
	 * @param infants
	 * @return the fixed infant price for airline
	 */
	private double getInfantPrice(String airlineCode, int infants) {
		double price = 0;
		if (airlineCode.equals(Airlines.IBERIA)) {
			price = Airlines.IBERIA_INFANT;
		}
		if (airlineCode.equals(Airlines.BRITISH_AIRWAYS)) {
			price = Airlines.BRITISH_AIRWAYS_INFANT;
		}
		if (airlineCode.equals(Airlines.LUFTHANSA)) {
			price = Airlines.LUFTHANSA_INFANT;
		}
		if (airlineCode.equals(Airlines.RYANAIR)) {
			price = Airlines.RYANAIR_INFANT;
		}
		if (airlineCode.equals(Airlines.VUELING)) {
			price = Airlines.VUELING_INFANT;
		}
		if (airlineCode.equals(Airlines.TURKISH_AIRLINES)) {
			price = Airlines.TURKISH_AIRLINES_INFANT;
		}
		if (airlineCode.equals(Airlines.EASYJET)) {
			price = Airlines.EASYJET_INFANT;
		}
		return infants * price;
	}

	/**
	 * This method calculates the exact price for each result flight.
	 * It has into account the number of days, adults, children and infants.
	 *
	 * @param listResultFlights
	 * @param days
	 * @param adults
	 * @param children
	 * @param infants
	 * @return a list of flights with their prices
	 */
	public List<ResultFlight> calculateExactPrice(List<ResultFlight> listResultFlights, int days, int adults, int children, int infants) {
		List<ResultFlight> listCalculated = new LinkedList<>();
		double infantPrice = 0;
		//Calculate the base price based on the days
		double percentage = getBasePercentage(days);
		for (ResultFlight rs : listResultFlights) {
			//For each flight, set the parameters
			rs.setAdults(adults);
			rs.setChildren(children);
			rs.setInfants(infants);
			//Calculate the price
			double price = (rs.getTotalPrice() * adults) * percentage;
			if (children > 0) {
				//We have children, so we have to calculate the amount with the child_base_price
				price = price + (CHILD_BASE_PRICE * children * rs.getTotalPrice()) * percentage;
			}
			if (infants > 0) {
				//We have infants, so it is mandatory to calculate its base price for each airline
				String airlineCode = rs.getFlightID().substring(0, 2);
				infantPrice = getInfantPrice(airlineCode, infants);
				price = price + infantPrice;
			}
			rs.setTotalPrice(price);
			listCalculated.add(rs);
		}
		return listCalculated;
	}

}
