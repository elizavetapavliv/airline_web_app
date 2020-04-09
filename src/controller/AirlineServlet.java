package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.command.*;
import model.command.get.*;
import model.command.post.*;
import model.exception.AirlineException;
import model.exception.DAOException;

/**Airline servlet*/
@WebServlet(name = "AirlineServlet", urlPatterns = "/Airline")
public class AirlineServlet extends HttpServlet {

	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Command manager*/
	private CommandManager commandManager;
	
	/**Entity manager factory*/
	//@PersistenceUnit(unitName="Airline")
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void init() {
		entityManagerFactory = (EntityManagerFactory)getServletContext().getAttribute("emf");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext servletContext = getServletContext();
		
		String page = request.getParameter("page");
		JSPOperation operation = new JSPOperation(request, response, servletContext, entityManagerFactory);
		JSPCommand command = null;
		switch (page) {
		case "airports":
			String action = request.getParameter("flight_action");
			if (action.equals("change_airport")) {
				command = new UpdateDestinationAirportCommand(operation);
			} 
			else {
				command = new DeleteFlightCommand(operation);
			}
			break;
		case "login":
			command = new LoginPostCommand(operation);
			break;
		case "registration":
			command = new RegistrationPostCommand(operation);
			break;
		case "logout":
			command = new LogoutCommand(operation); 
			break;			
		}
		
		commandManager = new CommandManager(command);
		try {
			commandManager.executeCommand();
		} 
		catch (AirlineException | DAOException e) {
			String message = e.getMessage();
			servletContext.log(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {	
		
		ServletContext servletContext = getServletContext();
		JSPOperation operation = new JSPOperation(request, response, servletContext, entityManagerFactory);
		JSPCommand command = null;
		
		
		String page = request.getParameter("page");
		if (page != null) {
			switch (page) {
			case "flights":
				command = new FlightsCommand(operation);
				break;
			case "delayed":
				command = new DelayedFlightsCommand(operation);
				break;
			case "brigade":				
				command = new FlightBrigadeCommand(operation);
				break;
			case "airports":
				command = new FlightAirportsCommand(operation); 
				break;
			case "login":
				Map<String, Object> map = entityManagerFactory.getProperties();
				String result = "Map: ";
				for(Entry<String, Object> entry: map.entrySet()) {
					result += entry.getKey() + " " + entry.getValue() + "; ";
				}
				response.sendError(500, result + "\nPassword: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.password")+
					"\nUser: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.user")+
					"\nUrl: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.url")+
					"\nDriver: " + entityManagerFactory.getProperties().get("javax.persistence.jdbc.driver"));
				return;
			/*	command = new LoginGetCommand(operation); 
				break;*/
			case "registration":
				command = new RegistrationGetCommand(operation); 
				break;				
			case "airline":
				command = new AirlineCommand(operation); 
				break;				
			}
		}
		else {
			command = new AirlineCommand(operation);
		}

		commandManager = new CommandManager(command);
		try {
			commandManager.executeCommand();
		} 
		catch (AirlineException | DAOException e) {
			String message = e.getMessage();
			servletContext.log(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  message);
		}

	}
}