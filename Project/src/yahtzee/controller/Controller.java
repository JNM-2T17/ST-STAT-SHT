package yahtzee.controller;

import yahtzee.controller.command.ICommand;
import yahtzee.model.*;
import yahtzee.view.*;

public class Controller implements IController {
	private MainFrame mainFrame;
	private AdvisorPanel advisorPanel;
	private GamePanel gamePanel;
	
	private DiceSet diceSet;
	private AdvisorModel advisor;
	
	public Controller() {
		diceSet = new DiceSet();
		advisor = new AdvisorModel( diceSet );
		System.out.println( diceSet );
		
		
		mainFrame = new MainFrame();
		
		gamePanel = new GamePanel( diceSet );
		mainFrame.setMain( gamePanel );
		
		advisorPanel = new AdvisorPanel();
		advisor.registerView( advisorPanel );
		advisor.setDiceSet( diceSet );
		advisor.setRerollNo( 2 );
		mainFrame.setSouth( advisorPanel );
		mainFrame.center();
	}
	
	public void executeCommand( ICommand command ) {
		command.execute();
	}
}