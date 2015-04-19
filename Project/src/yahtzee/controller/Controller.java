package yahtzee.controller;

import yahtzee.controller.command.ICommand;
import yahtzee.model.*;
import yahtzee.view.*;

public class Controller implements IController {
	private MainFrame mainFrame;
	private AdvisorPanel advisorPanel;
	private GamePanel gamePanel;
	private ScorePanel scorePanel;
	private PatternFinder patternFinder;
	
	private ScoreCard scoreCard;
	private DiceSet diceSet;
	private AdvisorModel advisor;
	
	public Controller() {
		diceSet = new DiceSet();
		scoreCard = ScoreCard.getInstance();
		advisor = new AdvisorModel( diceSet );
		patternFinder = PatternFinder.getInstance();
		patternFinder.setDice( diceSet );
		
		mainFrame = new MainFrame();
		
		gamePanel = new GamePanel( diceSet, this );
		mainFrame.setMain( gamePanel );
		
		scorePanel = new ScorePanel( scoreCard, this );
		mainFrame.setEast( scorePanel );
		
		advisorPanel = new AdvisorPanel();
		advisor.registerView( advisorPanel );
		advisor.setDiceSet( diceSet );
		mainFrame.setSouth( advisorPanel );
		
		mainFrame.center();
		
		patternFinder.registerView( scorePanel );
		scoreCard.registerView( scorePanel );
		diceSet.registerView( gamePanel );
		diceSet.registerAdvisor( advisor );
	}
	
	/**
	* executes a given command
	* @param command command to execute
	*/
	public void executeCommand( ICommand command ) {
		command.execute();
	}
}
