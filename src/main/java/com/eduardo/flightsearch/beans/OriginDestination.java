package com.eduardo.flightsearch.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>This class represents a bean of origin-destination.</p>
 * <p>It is used to look for flights in the search.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
@Getter
@Setter
public class OriginDestination implements Comparable<OriginDestination> {

	private String origin;
	private String destination;

	public OriginDestination(String origin, String destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	public static OriginDestination[] getFlighODs(Flight[] arrayFlights) {
		OriginDestination[] arrayOD = new OriginDestination[arrayFlights.length];
		for (int i = 0; i < arrayFlights.length; i++) {
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
		if (compare != 0)
			return compare;
		compare = getDestination().compareTo(od.getDestination());
		if (compare != 0)
			return compare;

		return 0;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		OriginDestination that = (OriginDestination) object;
		return java.util.Objects.equals(origin, that.origin) &&
				java.util.Objects.equals(destination, that.destination);
	}

	public int hashCode() {
		return java.util.Objects.hash(super.hashCode(), origin, destination);
	}
}
