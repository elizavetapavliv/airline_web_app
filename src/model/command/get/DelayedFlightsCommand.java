package model.command.get;

import java.io.IOException;

import javax.servlet.ServletException;
import model.command.JSPCommand;
import model.command.JSPOperation;
import model.exception.AirlineException;
import model.exception.DAOException;

/**Class representing command for getting delayed flights list*/
public class DelayedFlightsCommand extends JSPCommand {
	
	/**
	 * Constructor
	 * @param operation - operation to be performed
	 */
	public DelayedFlightsCommand(JSPOperation operation) {
		super(operation);
	}
	
	@Override
	public void execute() throws AirlineException, DAOException, ServletException, IOException {
		operation.getDelayedFlights();
	}

}
