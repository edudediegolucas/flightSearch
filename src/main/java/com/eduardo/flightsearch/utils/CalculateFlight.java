package com.eduardo.flightsearch.utils;

import com.eduardo.flightsearch.beans.ResultFlight;

import java.util.List;

/**
 * <p>This class contains all the logic of the project to abstract inner classes.</p>
 * <p>It's called from the servlet and the jUnit tests.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class CalculateFlight {

	public CalculateFlight() {
		super();
	}

	/**
	 * Method that calls inner classes to calculate flights.
	 *
	 * @param origin
	 * @param destination
	 * @param days
	 * @param adults
	 * @param children
	 * @param infants
	 * @return ResultFlight[]
	 */
	public ResultFlight[] getResults(String origin, String destination, int days, int adults, int children, int infants) {
		//First, search and find the flights
		SearchFlight sf = new SearchFlight();
		List<ResultFlight> listResultFlights = sf.searchFlightAlgorithm(origin, destination);
		//Once we have our flights, calculate their prices with all the parameters
		CalculatePriceFlight cpf = new CalculatePriceFlight();
		List<ResultFlight> listResultFlightsCalculated = cpf.calculateExactPrice(listResultFlights, days, adults, children, infants);
		//Return the ordered array of results 
		return sf.orderArrayResultFlights(listResultFlightsCalculated);
	}

}
