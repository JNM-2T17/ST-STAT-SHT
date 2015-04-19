package yahtzee.controller.command;

import yahtzee.model.ScoreCard;
import yahtzee.model.PatternFinder;

/**
* This class represents a command saying to roll the dice.
* @author Austin Fernandez
*/
public class LockCommand implements ICommand {
	
	/**
	* basic constructor
	* @param combo row to fill out
	*/
	public LockCommand() {
	}
	
	/**
	* rolls the dice not kept as described by the objects attributes
	*/
	public void execute() {
		PatternFinder.getInstance().getDice().lock();
	}
}