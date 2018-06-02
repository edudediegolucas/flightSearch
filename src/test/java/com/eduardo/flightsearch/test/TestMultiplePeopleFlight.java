package com.eduardo.flightsearch.test;

import com.eduardo.flightsearch.beans.ResultFlight;
import com.eduardo.flightsearch.constants.Airports;
import com.eduardo.flightsearch.utils.CalculateFlight;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Test Class for testing flights with N adult, N children and N infant.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class TestMultiplePeopleFlight {

	/**
	 * Test flight from Madrid to Barcelona 32 days
	 * 3 adult, 2 child and 1 infant
	 */
	@Test
	public void testMADBCN_32days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 32, 3, 2, 1);
		Assert.assertNotNull(array);
		Assert.assertEquals(1, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 646.0f, 0.1f);
	}

	/**
	 * Test flight from Paris to London 12 days
	 * 4 adult, 1 child and 4 infant
	 */
	@Test
	public void testCDGLHR_12days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.PARIS, Airports.LONDON, 12, 4, 1, 4);
		Assert.assertNotNull(array);
		Assert.assertEquals(2, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9813");
		Assert.assertEquals(array[1].getFlightID(), "U29718");
		Assert.assertEquals(array[0].getTotalPrice(), 921.8f, 0.1f);
		Assert.assertEquals(array[1].getTotalPrice(), 598.7f, 0.1f);
	}

	/**
	 * Test flight from Paris to London 11 days
	 * 2 adult, 8 child and 2 infant
	 */
	@Test
	public void testCDGLHR_11days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.PARIS, Airports.LONDON, 11, 2, 8, 2);
		Assert.assertNotNull(array);
		Assert.assertEquals(2, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9813");
		Assert.assertEquals(array[1].getFlightID(), "U29718");
		Assert.assertEquals(array[0].getTotalPrice(), 768.7f, 0.1f);
		Assert.assertEquals(array[1].getTotalPrice(), 484.8f, 0.1f);
	}


}
