package com.eduardo.flightsearch.utils;

import com.eduardo.flightsearch.beans.Flight;
import com.eduardo.flightsearch.beans.OriginDestination;
import com.eduardo.flightsearch.beans.ResultFlight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>This class has the whole logic of the project: searching flights.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class SearchFlight {

	public SearchFlight() {
		super();
	}

	/**
	 * Order the list of flights read from the csv file.
	 *
	 * @param listFlights
	 * @return a sorted array of flights
	 */
	private Flight[] orderArrayFlights(List<Flight> listFlights) {
		Flight[] arrayFlights = listFlights.toArray(new Flight[listFlights.size()]);
		Arrays.sort(arrayFlights);

		return arrayFlights;
	}

	/**
	 * Order the list of result flights.
	 *
	 * @param listResultFlights
	 * @return a sorted array of results
	 */
	public ResultFlight[] orderArrayResultFlights(List<ResultFlight> listResultFlights) {
		ResultFlight[] arrayResultsFlights = listResultFlights.toArray(new ResultFlight[listResultFlights.size()]);
		Arrays.sort(arrayResultsFlights);

		return arrayResultsFlights;
	}

	/**
	 * Convert the array of flights into a linked list in order to remove nodes of the list.
	 *
	 * @param arrayFlights
	 * @return a linked list of flights
	 */
	private List<Flight> convertToLinkedListFlights(Flight[] arrayFlights) {
		return new LinkedList<>(Arrays.asList(arrayFlights));
	}

	/**
	 * <p>Algorithm of flight search.</p>
	 * <p>It is based on <b>Binary Search</b>: it divides the arrays into two and each time
	 * it finds one match, this match is removed from the auxiliary list, then the binary search
	 * is called all times needed until there is no more matches.</p>
	 * <p>The search is done in an origin-destination array because they are the only two possibilities
	 * to look for.</p>
	 * <p>Its complexity is O(log(n))</p>
	 *
	 * @param listFlightAux
	 * @param arrayOD
	 * @param od
	 * @return a list of flights
	 */
	private List<ResultFlight> searchFlight(List<Flight> listFlightAux, OriginDestination[] arrayOD, OriginDestination od) {
		List<ResultFlight> listResults = new ArrayList<>();
		int pos = 0;
		while (pos >= 0) {
			pos = Arrays.binarySearch(arrayOD, od);
			if (pos > 0) {
				ResultFlight rs = new ResultFlight(listFlightAux.get(pos).getFlightID(), listFlightAux.get(pos).getPrice(), 0, 0, 0);
				listResults.add(rs);
				listFlightAux.remove(pos);
				Flight[] arrayFlightsAux = listFlightAux.toArray(new Flight[listFlightAux.size()]);
				arrayOD = OriginDestination.getFlighODs(arrayFlightsAux);
			}
		}
		return listResults;
	}

	/**
	 * This method contains all the step to call the search algorithm.
	 *
	 * @param origin
	 * @param destination
	 * @return a list of flights
	 */
	public List<ResultFlight> searchFlightAlgorithm(String origin, String destination) {
		OriginDestination od = new OriginDestination(origin, destination);
		//Create the list of flights
		List<Flight> listFlights = ParseCSVFile.copyFromCSV();
		//Order as an array of flights
		Flight[] arrayFlights = orderArrayFlights(listFlights);
		//Convert it into a linked list
		List<Flight> listFlightAux = convertToLinkedListFlights(arrayFlights);
		//Create an origin-destination object for the search
		OriginDestination[] arrayOD = OriginDestination.getFlighODs(arrayFlights);
		//Call the algorithm
		return searchFlight(listFlightAux, arrayOD, od);
	}

}
