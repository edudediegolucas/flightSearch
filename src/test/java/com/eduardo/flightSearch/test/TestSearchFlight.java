package com.eduardo.flightSearch.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.eduardo.flightSearch.beans.ResultFlight;
import com.eduardo.flightSearch.constants.Airports;
import com.eduardo.flightSearch.utils.SearchFlight;

/**
 * <p>Test Class for testing searching flights algorithm.</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class TestSearchFlight {
	
	/**
	 * Test search flight algorithm with MADRID and BARCELONA
	 */
	@Test
	public void testSearchFlightMAD_BCN(){
		SearchFlight sf = new SearchFlight();
		List<ResultFlight> listResultFlights = sf.searchFlightAlgorithm(Airports.MADRID, Airports.BARCELONA);
		Assert.assertNotNull(listResultFlights);
		Assert.assertEquals(listResultFlights.size(), 1);
	}
	
	/**
	 * Test search flight algorithm with AMSTERDAM and FRANKFURT
	 */
	@Test
	public void testSearchFlightAMS_FRA(){
		SearchFlight sf = new SearchFlight();
		List<ResultFlight> listResultFlights = sf.searchFlightAlgorithm(Airports.AMSTERDAM, Airports.FRANKFURT);
		Assert.assertNotNull(listResultFlights);
		Assert.assertEquals(listResultFlights.size(), 3);
	}
	
	/**
	 * Test search flight algorithm with ROME and COPENHAGEN
	 */
	@Test
	public void testSearchFlightFCO_CPH(){
		SearchFlight sf = new SearchFlight();
		List<ResultFlight> listResultFlights = sf.searchFlightAlgorithm(Airports.ROME, Airports.COPENHAGEN);
		Assert.assertNotNull(listResultFlights);
		Assert.assertEquals(listResultFlights.size(), 0);
	}

}
