package com.eduardo.flightsearch.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <p>This class represents the object Flight once read from the csv file.</p>
 * <p>It implements <b>Comparable</b> interface in order to be sorted.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
@Getter
@Setter
public class Flight implements Comparable<Flight> {

	private String origin;
	private String destination;
	private String flightID;
	private int price;

	public Flight(String flightLine) {
		String[] lines = flightLine.split(",");
		this.origin = lines[0];
		this.destination = lines[1];
		this.flightID = lines[2];
		this.price = Integer.parseInt(lines[3].replace("€", "").trim());
	}

	/**
	 * <p>Method that compares flights.</p>
	 * <p>Firstly, it compares lexicographically the origin.</p>
	 * <p>Secondly and only if <code>compare</code> turns 0, it compares lexicographically the destination.</p>
	 * <p>Finally, it compares the prices.</p>
	 */
	@Override
	public int compareTo(Flight flightToCompare) {
		int compare = getOrigin().compareTo(flightToCompare.getOrigin());
		if (compare != 0)
			return compare;
		compare = getDestination().compareTo(flightToCompare.getDestination());
		if (compare != 0)
			return compare;
		return Integer.compare(getPrice(), flightToCompare.getPrice());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return price == flight.price &&
				Objects.equals(origin, flight.origin) &&
				Objects.equals(destination, flight.destination) &&
				Objects.equals(flightID, flight.flightID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(origin, destination, flightID, price);
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", flightID=" + flightID + ", price=" + price + "€]";
	}
}
