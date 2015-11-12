package com.eduardo.flightSearch.test;

import org.junit.Assert;
import org.junit.Test;

import com.eduardo.flightSearch.beans.ResultFlight;
import com.eduardo.flightSearch.constants.Airports;
import com.eduardo.flightSearch.utils.CalculateFlight;

/**
 * <p>Test Class for testing flights with 1 adult, 1 child and 1 infant.</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class Test1Adult1Child1InfantFlight {

	/**
	 * Test flight from Madrid to Barcelona 32 days
	 * 1 adult, 1 child and 1 infant
	 */
	@Test
	public void testMADBCN_32days(){
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 32, 1, 1, 1);
		Assert.assertNotNull(array);
		Assert.assertEquals(array.length, 1);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 237.7f, 0.1f);
	}
	
	/**
	 * Test flight from Madrid to Barcelona 18 days
	 * 1 adult, 1 child and 1 infant
	 */
	@Test
	public void testMADBCN_18days(){
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 18, 1, 1, 1);
		Assert.assertNotNull(array);
		Assert.assertEquals(array.length, 1);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 293.4f, 0.1f);
	}
	
	/**
	 * Test flight from Madrid to Barcelona 5 days
	 * 1 adult, 1 child and 1 infant
	 */
	@Test
	public void testMADBCN_5days(){
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 5, 1, 1, 1);
		Assert.assertNotNull(array);
		Assert.assertEquals(array.length, 1);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 349.1f, 0.1f);
	}
	
	/**
	 * Test flight from Madrid to Barcelona 2 days
	 * 1 adult, 1 child and 1 infant
	 */
	@Test
	public void testMADBCN_2days(){
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 2, 1, 1, 1);
		Assert.assertNotNull(array);
		Assert.assertEquals(array.length, 1);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 432.6f, 0.1f);
	}
	
	
}
