package model.command;

import java.io.IOException;

import javax.servlet.ServletException;

import model.exception.AirlineException;
import model.exception.DAOException;

/**Class for executing command from jsp*/
public class CommandManager {

	/**Command from jsp*/
	private JSPCommand command;
	
	/**
	 * Constructor
	 * @param command - to be executed
	 */
	public CommandManager(JSPCommand command) {
		this.setCommand(command);
	}

	/**
	 * Function for getting command
	 * @return command
	 */
	public JSPCommand getCommand() {
		return command;
	}

	/**
	 * Function for setting command
	 * @param command - command to be executed
	 */
	public void setCommand(JSPCommand command) {
		this.command = command;
	}
	
	/**
	 * Function for executing command
	 * @throws AirlineException - airline exception
	 * @throws DAOException - dao exception
	 * @throws ServletException - servlet exception
	 * @throws IOException - io exception
	 */
	public void executeCommand() throws AirlineException, DAOException, ServletException, IOException {
		command.execute();
	}
}
