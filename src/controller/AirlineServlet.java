package controller;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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
@PersistenceContext(unitName="my-pu", name="persistence/AppMgr")
public class AirlineServlet extends HttpServlet {

	/**Serial version UID*/
	private static final long serialVersionUID = 1L;

	/**Command manager*/
	private CommandManager commandManager;
	
	/**Entity manager*/
	//@PersistenceContext(name="persistence/AppMgr" unitName = "Airline")
	//private EntityManager entityManager;
	
	  @PersistenceUnit(unitName = "Airline")
	  private EntityManagerFactory emf;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext servletContext = getServletContext();	
		
		EntityManager em = null ;
		  try {
			  InitialContext ic = new InitialContext();
			 em =
			   (EntityManager) ic.lookup("java:comp/env/persistence/AppMgr");
			servletContext.log("EM: " + em);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		servletContext.log("EMF: " + emf);
		JSPOperation operation = new JSPOperation(request, response, servletContext, em);
		JSPCommand command = null;
		servletContext.log("EMF2: " + emf);
		
		String page = request.getParameter("page");
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
		case "brigade":
			command = new UpdateBrigadeCommand(operation);
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
		JSPOperation operation = new JSPOperation(request, response, servletContext, emf.createEntityManager());
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
				command = new LoginGetCommand(operation); 
				break;
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