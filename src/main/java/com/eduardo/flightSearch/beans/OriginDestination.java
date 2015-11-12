package com.eduardo.flightSearch.beans;

/**
 * <p>This class represents a bean of origin-destination.</p>
 * <p>It is used to look for flights in the search.</p> 
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class OriginDestination implements Comparable<OriginDestination>{
	
	private String origin;
	private String destination;
	
	public OriginDestination() {
		super();
	}

	public OriginDestination(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
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
	
	public static OriginDestination[] getFlighODs(Flight[] arrayFlights){
		OriginDestination[] arrayOD = new OriginDestination[arrayFlights.length];
		for(int i=0;i<arrayFlights.length;i++){
			arrayOD[i] = new OriginDestination(arrayFlights[i].getOrigin(), arrayFlights[i].getDestination());
		}
		return arrayOD;
	}

	/**
	 * <p>Method that compares origin-destination objects.</p>
	 * <p>Firstly, it compares lexicographically the origin.</p>
	 * <p>Finally and only if <code>compare</code> turns 0, it compares lexicographically the destination.</p>
	 */
	@Override
	public int compareTo(OriginDestination od) {
		int compare = getOrigin().compareTo(od.getOrigin());
		if(compare!=0)
			return compare;
		compare = getDestination().compareTo(od.getDestination());
		if(compare!=0)
			return compare;
		return 0;
	}
}
