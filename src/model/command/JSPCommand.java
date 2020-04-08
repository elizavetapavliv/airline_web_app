package model.command;

import java.io.IOException;

import javax.servlet.ServletException;

import model.exception.AirlineException;
import model.exception.DAOException;

/**Class representing jsp command*/
public abstract class JSPCommand {
	
	/**Operation to be performed*/
	protected JSPOperation operation;
	
	/**
	 * Constructor
	 * @param operation - operation to be performed
	 */
	public JSPCommand(JSPOperation operation) {
		this.operation = operation;
	}

	/**
	 * Function for execution operation
	 * @throws AirlineException - airline exception
	 * @throws DAOException - dao exception
	 * @throws ServletException - servlet exception
	 * @throws IOException - io exception
	 */
	public abstract void execute() throws AirlineException, DAOException, ServletException, IOException;
}