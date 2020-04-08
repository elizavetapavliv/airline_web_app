package model.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.*;
import model.entity.Brigade;
import model.entity.Flight;
import model.entity.User;
import model.exception.AirlineException;
import model.exception.DAOException;
import model.entity.Airport;

/**Class representing performing operation from request*/
public class JSPOperation {
	
	/**DAO flight object*/
	private DAOFlight daoFlight;
	
	/**DAO brigade object*/
	private DAOBrigade daoBrigade;
	
	/**DAO plane object*/
	private DAOPlane daoPlane;
	
	/**DAO airport object*/
	private DAOAirport daoAirport;
	
	/**DAO user object*/
	private DAOUser daoUser;
	
	/**Servlet request object*/
	private HttpServletRequest request;
	
	/**Servlet response object*/
	private HttpServletResponse response;
	
	/**Servlet context object*/
	private ServletContext servletContext;
	
	/**
	 * Constructor
	 * @param request - http servlet request
	 * @param response - http servlet response
	 * @param servletContext - http servlet context
	 * @param entityManagerFactory - entity manager factory for dao
	 */
	public JSPOperation(HttpServletRequest request, HttpServletResponse response, 
			ServletContext servletContext, EntityManagerFactory entityManagerFactory) {		
		daoFlight = new DAOFlight(entityManagerFactory);
		daoBrigade = new DAOBrigade(entityManagerFactory);
		daoPlane = new DAOPlane(entityManagerFactory);
		daoAirport = new DAOAirport(entityManagerFactory);
		daoUser = new DAOUser(entityManagerFactory);
		this.request = request;
		this.response = response;
		this.servletContext = servletContext;
	}
	
	/**
	 * Function for getting all flights
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws AirlineException - when there are no flights
	 * @throws IOException - something wrong with servlet request
	 * @throws ServletException - something wrong with servlet request 
	 */
	public void getAllFlights() throws AirlineException, DAOException, ServletException, IOException {
		List<Flight> flights = daoFlight.readAllFlights();
		if (flights.size() == 0) {
			throw new AirlineException("There are no flights");
		}
		servletContext.log("Got list of all flights");
		request.setAttribute("flights", flights);
		request.getRequestDispatcher(Navigation.flightsUri).forward(request, response);
	}
	
	/**
	 * Function for getting delayed flights
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws AirlineException - when there are no delayed flights
	 * @throws IOException - something wrong with servlet request
	 * @throws ServletException - something wrong with servlet request 
	 */
	public void getDelayedFlights() throws AirlineException, DAOException, ServletException, IOException {
		List<Flight> flights = daoFlight.readDelayedFlights();
		if (flights.size() == 0) {
			throw new AirlineException("There are no delayed flights");
		}
		servletContext.log("Got list of delayed flights");
		request.setAttribute("flights", flights);
		request.getRequestDispatcher(Navigation.delayedFlightsUri).forward(request, response);
	}
	
	
	/**
	 * Function for getting flight brigade
	 * @param flightId - flight id
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws AirlineException - when flight has no brigade
	 * @throws IOException - something wrong with servlet request 
	 * @throws ServletException - something wrong with servlet request 
	 */
	public void getFlightBrigade() throws AirlineException, DAOException, ServletException, IOException {
		String flightId = request.getParameter("flight_id_brigade");
		if (flightId != null) {
			Brigade brigade = daoBrigade.readBrigadeByFlight(Integer.parseInt(flightId));
			if (brigade == null) {
				throw new AirlineException("Brigade wasn't assigned to this flight or flight doesn't exist");
			}
			servletContext.log("Got brigade of the flight");
			request.setAttribute("brigade", brigade);
		}
		request.getRequestDispatcher(Navigation.brigadeUri).forward(request, response);
	}
	
	/**
	 * Function for getting flight destination and departure airports
	 * @param flightId - flight id
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws AirlineException - when flight doesn't exist 
	 * @throws IOException - something wrong with servlet request 
	 * @throws ServletException - something wrong with servlet request 
	 */
	public void getFlightAirports()
			throws DAOException, AirlineException, ServletException, IOException {
		String flightId = request.getParameter("flight_id_airports");
		if (flightId != null) {
			int id = Integer.parseInt(flightId);
			Flight flight = daoFlight.readFlight(id);
			if (flight == null) {
				throw new AirlineException("Flight with this id doesn't exist");
			}
			servletContext.log("Got flight data");
			int fromAirportId = flight.getFromAirportId();
			int toAirportId = flight.getToAirportId();

			List<Airport> toFromAirports = daoAirport.readAirports(fromAirportId, toAirportId);
			servletContext.log("Got departure and destination airoports of flight");
			request.setAttribute("airports", toFromAirports);
			request.setAttribute("flight_id", id);
			request.setAttribute("from_airport_id", fromAirportId);
		}
		request.getRequestDispatcher(Navigation.airportsUri).forward(request, response);
	}
	
	/**
	 * Function for deleting flight
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws IOException - something wrong with servlet request 
	 * @throws ServletException - something wrong with servlet request 
	 */
	public void deleteFlight() throws DAOException, ServletException, IOException {
		int flightId = Integer.parseInt(request.getParameter("flight_id"));
		daoPlane.updatePlaneFlight(flightId);
		servletContext.log("Freed plane of flight");
		daoBrigade.updateBrigadeFlight(flightId);
		servletContext.log("Freed brigade of flight");
		daoFlight.deleteFlight(flightId);
		servletContext.log("Canceled flight");
		request.getRequestDispatcher(Navigation.airportsUri).forward(request, response);
	}
	
	/**
	 * Function for updating flight destination airport
	 * @throws DAOException - when connection or query execution aren't successful 
	 * @throws IOException - something wrong with servlet request 
	 * @throws ServletException - something wrong with servlet request 
	 * @throws AirlineException - if destination airport id is incorrect
	 */
	public void updateDestinationAirport() throws DAOException, ServletException, IOException, AirlineException {
		int flightId = Integer.parseInt(request.getParameter("flight_id"));
		int fromAirportId = Integer.parseInt(request.getParameter("flight_id"));
		int airportId = Integer.parseInt(request.getParameter("airport_id"));
		if (airportId == fromAirportId) {
			throw new AirlineException("Destination airport cannot match departure airport");
		} 
		List<Airport> airports = daoAirport.readAllAirports();
		int size = airports.size();
		int i;
		for (i = 0; i < size; i++) {
			if (airports.get(i).getId() == airportId) {
				break;
			}
		}
		if (i == size) {
			throw new AirlineException("Airport with this id doesn't exist");
		}
		daoFlight.updateFlightToAirport(flightId, airportId);
		servletContext.log("Changed destination airport of the flight");
		request.getRequestDispatcher(Navigation.airportsUri).forward(request, response);
	}
	
	public void goToLoginPage() throws ServletException, IOException {
		request.getRequestDispatcher(Navigation.loginUri).forward(request, response);
	}
	
	public void goToRegistrationPage() throws ServletException, IOException {
		request.getRequestDispatcher(Navigation.registrationUri).forward(request, response);
	}
	
	public void goToAirlinePage() throws ServletException, IOException {
		request.getRequestDispatcher(Navigation.airlineUri).forward(request, response);
	}
	
	public void registrateUser() throws DAOException, ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String adminCode = request.getParameter("admin_code");

		User user = new User(email, password);
		if (!adminCode.isEmpty()) {
			user.setType("admin");
		} 
		else {
			user.setType("user");
		}
		daoUser.createUser(user);
		startNewSessionAndUpdateCookies(user);
		request.getRequestDispatcher(Navigation.airlineUri).forward(request, response);
	}
	
	public void loginUser() throws AirlineException, DAOException, ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		User user;
		
		if(email == null && password == null) {
			user = new User();
			user.setType("guest");
		}
		else {
			user = daoUser.readUser(email);
			if(user == null) {
				throw new AirlineException("Login failed. Email is incorrect.");
			}
			if(!password.equals(user.getPassword())) {
				throw new AirlineException("Login failed. Password is incorrect.");
			}
		}
		startNewSessionAndUpdateCookies(user);	
		request.getRequestDispatcher(Navigation.airlineUri).forward(request, response);
	}
	
	public void logoutUser() throws ServletException, IOException {
		HttpSession session = request.getSession();
	    session.setAttribute("user", null);
	    request.getRequestDispatcher(Navigation.loginUri).forward(request, response);
	}
	
	/**
	 * Function for getting session and updating cookies
	 * @param user - user for saving info in session
	 */
	private void startNewSessionAndUpdateCookies(User user) {
		HttpSession session = request.getSession();
	    session.setAttribute("user", user);
		
		Cookie lastSessionDateTime = new Cookie("lastSessionDateTime",  
				"\"" + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + "\"");
		lastSessionDateTime.setComment("Time and date of the last user session");
		Cookie numberOfVisits = new Cookie("numberOfVisits", "1");
		numberOfVisits.setComment("The number of visits to the resource.");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("numberOfVisits")) {
					int previousNumberOfVisits = Integer.parseInt(cookie.getValue());
					numberOfVisits.setValue(Integer.toString(previousNumberOfVisits + 1));
				}
			}
		}
		response.addCookie(lastSessionDateTime);
		response.addCookie(numberOfVisits);
	}
	
}
