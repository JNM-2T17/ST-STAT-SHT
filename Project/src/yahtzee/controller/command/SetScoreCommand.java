package yahtzee.controller.command;

import yahtzee.model.ScoreCard;
import yahtzee.model.PatternFinder;

/**
* This class represents a command saying to roll the dice.
* @author Austin Fernandez
*/
public class SetScoreCommand implements ICommand {
	private String combo;
	
	/**
	* basic constructor
	* @param combo row to fill out
	*/
	public SetScoreCommand( String combo ) {
		this.combo = combo;
	}
	
	/**
	* rolls the dice not kept as described by the objects attributes
	*/
	public void execute() {
		ScoreCard sc = ScoreCard.getInstance();
		PatternFinder pf = PatternFinder.getInstance();
		
		int[] dist = pf.getDice().getDistribution();
		for( int i : dist ) {
			if( i == 5 ) {
				if( sc.get( ScoreCard.YAHTZEE ) == 50 ) {
					sc.extraYahtzee( combo, pf.getDice() );
				} else {
					sc.set( combo, pf.getDice() );
				}
				if( !sc.isGameFinished() ) {
					pf.getDice().reset();
				}
				return;
			}
		}
		
		sc.set( combo, pf.getDice() );
		if( !sc.isGameFinished() ) {
			pf.getDice().reset();
		}
	}
}