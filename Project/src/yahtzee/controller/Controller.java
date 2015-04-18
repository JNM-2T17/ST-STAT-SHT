package yahtzee.controller;

import yahtzee.controller.command.ICommand;
import yahtzee.model.*;
import yahtzee.view.MainFrame;

public class Controller implements IController {
	private DiceSet diceSet;
	private MainFrame mainFrame;
	
	public Controller() {
		diceSet = new DiceSet();
		mainFrame = new MainFrame();
	}
	
	public void executeCommand( ICommand command ) {
		command.execute();
	}
}