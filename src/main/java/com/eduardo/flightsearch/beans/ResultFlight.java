package com.eduardo.flightsearch.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Objects;

/**
 * <p>This class is the final representation of the results given by the flight search.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
@Getter
@Setter
public class ResultFlight implements Comparable<ResultFlight> {

	private String flightID;
	private double totalPrice;
	private int adults;
	private int children;
	private int infants;

	public ResultFlight() {
		super();
	}

	public ResultFlight(String flightID, double totalPrice, int adults, int children, int infants) {
		super();
		this.flightID = flightID;
		this.totalPrice = totalPrice;
		this.adults = adults;
		this.children = children;
		this.infants = infants;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ResultFlight that = (ResultFlight) o;
		return Double.compare(that.totalPrice, totalPrice) == 0 &&
				adults == that.adults &&
				children == that.children &&
				infants == that.infants &&
				Objects.equals(flightID, that.flightID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flightID, totalPrice, adults, children, infants);
	}

	/**
	 * <p>Method that compares flight results and orders it in descending way</p>
	 */

	@Override
	public int compareTo(ResultFlight resultFlightToCompare) {
		return (Double.compare(getTotalPrice(), resultFlightToCompare.getTotalPrice())) * (-1);
	}

	@Override
	public String toString() {
		return flightID + ", " + String.format(Locale.US, "%.1f", totalPrice) + " €, for " + adults + " adult(s), " + children + " children and " + infants + " infants.";
	}

}
