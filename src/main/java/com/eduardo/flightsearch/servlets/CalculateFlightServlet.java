package com.eduardo.flightsearch.servlets;

import com.eduardo.flightsearch.beans.ResultFlight;
import com.eduardo.flightsearch.utils.CalculateFlight;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * <p>Servlet for calculating flights</p>
 * <p>It uses the class {@link CalculateFlight} to calculate the flights using different parameters from the web</p>
 *
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 */
@Slf4j
public class CalculateFlightServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CalculateFlightServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtain parameters from request
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		log.info("Calling /flightResult with origin {} and destination {}", origin, destination);
		try {
			int days = Integer.parseInt(request.getParameter("days"));
			int adults = Integer.parseInt(request.getParameter("adults"));
			int children = Integer.parseInt(request.getParameter("children"));
			int infants = Integer.parseInt(request.getParameter("infants"));
			//Call calculate methods
			CalculateFlight cf = new CalculateFlight();
			ResultFlight[] array = cf.getResults(origin, destination, days, adults, children, infants);
			//Set to request
			request.setAttribute("array", array);
			request.getRequestDispatcher("result.jsp").forward(request, response);
		} catch (NumberFormatException nfe) {
			//There was an error calculating flights, forward to error
			log.error("Error calculating flights");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (UnknownHostException uhex) {
			log.error("Error redirecting", uhex);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (UnknownHostException uhex) {
			log.error("Error redirecting", uhex);
		}
	}
}
