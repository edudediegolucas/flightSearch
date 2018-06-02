package com.eduardo.flightsearch.utils;

import com.eduardo.flightsearch.beans.Flight;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>This class is an utility for copy the lines read from the csv file.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class ParseCSVFile {

	private ParseCSVFile() {
	}

	/**
	 * Copy all string lines obtained reading the csv file.
	 * It uses Java NIO.
	 *
	 * @return a list of <code>Flight</code> objects
	 */
	public static List<Flight> copyFromCSV() {
		List<Flight> listFlights = new LinkedList<>();
		ReadCSVFile r = new ReadCSVFile();
		List<String> listFlightString = r.readCSVFile(ReadCSVFile.FILE_FLIGHTS_NAME);
		if ((listFlightString != null) && (!listFlightString.isEmpty())) {
			for (String flightLine : listFlightString) {
				Flight flight = new Flight(flightLine);
				listFlights.add(flight);
			}
		}
		return listFlights;
	}
}
