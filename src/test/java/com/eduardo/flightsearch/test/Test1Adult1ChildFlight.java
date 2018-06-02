package com.eduardo.flightsearch.test;

import com.eduardo.flightsearch.beans.ResultFlight;
import com.eduardo.flightsearch.constants.Airports;
import com.eduardo.flightsearch.utils.CalculateFlight;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Test Class for testing flights with 1 adult and 1 child.</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
public class Test1Adult1ChildFlight {

	/**
	 * Test flight from Madrid to Barcelona 32 days
	 * 1 adult and 1 child
	 */
	@Test
	public void testMADBCN_32days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 32, 1, 1, 0);
		Assert.assertNotNull(array);
		Assert.assertEquals(1, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 222.7f, 0.1f);
	}

	/**
	 * Test flight from Madrid to Barcelona 18 days
	 * 1 adult and 1 child
	 */
	@Test
	public void testMADBCN_18days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 18, 1, 1, 0);
		Assert.assertNotNull(array);
		Assert.assertEquals(1, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 278.4f, 0.1f);
	}

	/**
	 * Test flight from Madrid to Barcelona 5 days
	 * 1 adult and 1 child
	 */
	@Test
	public void testMADBCN_5days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 5, 1, 1, 0);
		Assert.assertNotNull(array);
		Assert.assertEquals(1, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 334.1f, 0.1f);
	}

	/**
	 * Test flight from Madrid to Barcelona 2 days
	 * 1 adult and 1 child
	 */
	@Test
	public void testMADBCN_2days() {
		CalculateFlight cf = new CalculateFlight();
		ResultFlight[] array = cf.getResults(Airports.MADRID, Airports.BARCELONA, 2, 1, 1, 0);
		Assert.assertNotNull(array);
		Assert.assertEquals(1, array.length);
		Assert.assertEquals(array[0].getFlightID(), "BA9569");
		Assert.assertEquals(array[0].getTotalPrice(), 417.6f, 0.1f);
	}

}
