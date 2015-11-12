package com.eduardo.flightSearch.beans;

import java.util.Locale;

/**
 * <p>This class is the final representation of the results given by the flight search.</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class ResultFlight implements Comparable<ResultFlight>{
	
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
	
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public int getInfants() {
		return infants;
	}
	public void setInfants(int infants) {
		this.infants = infants;
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
		return flightID + ", " + String.format(Locale.US, "%.1f", totalPrice) + " €, for " + adults + " adult(s), " + children	+ " children and " + infants + " infants.";
	}

}
