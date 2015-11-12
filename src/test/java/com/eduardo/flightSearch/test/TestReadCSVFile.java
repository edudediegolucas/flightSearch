package com.eduardo.flightSearch.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.eduardo.flightSearch.beans.Flight;
import com.eduardo.flightSearch.utils.ParseCSVFile;

/**
 * <p>Test Class for testing csv file read.</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class TestReadCSVFile {
	
	/**
	 * Test if list read from csv file is not null
	 */
	@Test
	public void testReadCSVFile(){
		List<Flight> listFlights = ParseCSVFile.copyFromCSV();
		Assert.assertNotNull(listFlights);
	}
	
	/**
	 * Test if list contains 89 elements
	 */
	@Test
	public void testSizeListCSVFile(){
		List<Flight> listFlights = ParseCSVFile.copyFromCSV();
		Assert.assertEquals(listFlights.size(), 89);
	}

}
