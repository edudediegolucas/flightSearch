package com.eduardo.flightSearch.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eduardo.flightSearch.beans.ResultFlight;
import com.eduardo.flightSearch.utils.CalculateFlight;

/**
 * <p>Servlet for calculating flights</p>
 * <p>It uses the class {@link CalculateFlight} to calculate the flights using different parameters from the web</p>
 * @author Eduardo de Diego Lucas
 * @see <a href="http://eduardodediegolucas.es">eduardodediegolucas.es</a>
 *
 */
public class CalculateFlightServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private String origin, destination = null;
	private int days, adults, children, infants = 0;
	
	public CalculateFlightServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtain parameters from request
		origin = request.getParameter("origin");
		destination = request.getParameter("destination");
		try{
			days = Integer.parseInt(request.getParameter("days"));
			adults = Integer.parseInt(request.getParameter("adults"));
			children = Integer.parseInt(request.getParameter("children"));
			infants = Integer.parseInt(request.getParameter("infants"));
			//Call calculate methods
			CalculateFlight cf = new CalculateFlight();
			ResultFlight[] array = cf.getResults(origin, destination, days, adults, children, infants);
			//Set to request
			request.setAttribute("array", array);
			request.getRequestDispatcher("result.jsp").forward(request, response);
		}catch(NumberFormatException nfe){
			//There was an error calculating flights, forward to error
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
