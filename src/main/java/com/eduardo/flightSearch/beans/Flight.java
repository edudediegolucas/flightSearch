package com.eduardo.flightSearch.beans;

/**
 * <p>This class represents the object Flight once read from the csv file.</p>
 * <p>It implements <b>Comparable</b> interface in order to be sorted.</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class Flight implements Comparable<Flight>{
	
	private String origin;
	private String destination;
	private String flightID;
	private int price;
		
	public Flight() {
		super();
	}

	public Flight(String origin, String destination, String flightID, int price) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightID = flightID;
		this.price = price;
	}
	
	public Flight(String flightLine){
		String[] lines = flightLine.split(",");
		this.origin = lines[0];
		this.destination = lines[1];
		this.flightID = lines[2];
		this.price = Integer.parseInt(lines[3].replace("€", "").trim());
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * <p>Method that compares flights.</p>
	 * <p>Firstly, it compares lexicographically the origin.</p>
	 * <p>Secondly and only if <code>compare</code> turns 0, it compares lexicographically the destination.</p>
	 * <p>Finally, it compares the prices.</p>
	 */
	@Override
	public int compareTo(Flight flightToCompare){
		int compare = getOrigin().compareTo(flightToCompare.getOrigin());
		if(compare!=0)
			return compare;
		compare = getDestination().compareTo(flightToCompare.getDestination());
		if(compare!=0)
			return compare;
		return Integer.compare(getPrice(), flightToCompare.getPrice());
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", flightID=" + flightID + ", price=" + price + "€]";
	}
}
