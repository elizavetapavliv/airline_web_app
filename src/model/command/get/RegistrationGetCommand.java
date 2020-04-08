package model.command.get;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.JSPCommand;
import model.command.JSPOperation;
import model.exception.AirlineException;
import model.exception.DAOException;

/**Class representing command for going to registration page*/
public class RegistrationGetCommand extends JSPCommand{

	/**
	 * Constructor
	 * @param operation - operation to be performed
	 */
	public RegistrationGetCommand(JSPOperation operation) {
		super(operation);
	}
	
	@Override
	public void execute() throws AirlineException, DAOException, ServletException, IOException {	
		operation.goToRegistrationPage();
	}
}
