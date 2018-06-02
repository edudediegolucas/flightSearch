package com.eduardo.flightsearch.test;

import com.eduardo.flightsearch.beans.Flight;
import com.eduardo.flightsearch.utils.ParseCSVFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p>Test Class for testing csv file read.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class TestReadCSVFile {

	/**
	 * Test if list read from csv file is not null
	 */
	@Test
	public void testReadCSVFile() {
		List<Flight> listFlights = ParseCSVFile.copyFromCSV();
		Assert.assertNotNull(listFlights);
	}

	/**
	 * Test if list contains 89 elements
	 */
	@Test
	public void testSizeListCSVFile() {
		List<Flight> listFlights = ParseCSVFile.copyFromCSV();
		Assert.assertEquals(89, listFlights.size());
	}

}
