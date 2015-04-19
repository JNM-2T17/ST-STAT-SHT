package yahtzee.controller;

import yahtzee.controller.command.ICommand;

/**
* interface for controllers
* @author Austin Fernandez
*/
public interface IController {
	
	/**
	* executes a given command
	* @param command command to execute
	*/
	public void executeCommand( ICommand command );
}
