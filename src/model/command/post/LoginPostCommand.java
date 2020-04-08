package model.command.post;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.JSPCommand;
import model.command.JSPOperation;
import model.exception.AirlineException;
import model.exception.DAOException;

/**Class representing command for user login*/
public class LoginPostCommand extends JSPCommand{

	/**
	 * Constructor
	 * @param operation - operation to be performed
	 */
	public LoginPostCommand(JSPOperation operation) {
		super(operation);
	}

	@Override
	public void execute() throws AirlineException, DAOException, ServletException, IOException {
		operation.loginUser();
	}
}