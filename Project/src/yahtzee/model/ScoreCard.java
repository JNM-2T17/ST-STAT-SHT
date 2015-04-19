package yahtzee.model;

import yahtzee.view.IView;
import yahtzee.view.ScorePanel;

/**
* This class is a singleton class representing a score card for Yahtzee
* @author Kenneth Wang
*/
public class ScoreCard {
	private static ScoreCard scorecard = null; //single instance
	
	//constants to be used for all row parameters in this class
	/**constant for Aces row*/
	public static final String ACES = "Aces";
	/**constant for Twos row*/
	public static final String TWOS = "Twos";
	/**constant for Threes row*/
	public static final String THREES = "Threes";
	/**constant for Fours row*/	
	public static final String FOURS = "Fours";
	/**constant for Fives row*/
	public static final String FIVES = "Fives";
	/**constant for Sixes row*/
	public static final String SIXES = "Sixes";
	/**constant for Three of a Kind row*/
	public static final String THREE_OF_A_KIND = "3oak"; 
	/**constant for Four of a Kind row*/
	public static final String FOUR_OF_A_KIND = "4oak";
	/**constant for Full House row*/
	public static final String FULL_HOUSE = "Full House";
	/**constant for Small Straight row*/
	public static final String SMALL_STRAIGHT = "Small Straight";
	/**constant for Large Straight row*/
	public static final String LARGE_STRAIGHT = "Large Straight";
	/**constant for Yahtzee row*/
	public static final String YAHTZEE = "Yahtzee";
	/**constant for Chance row*/
	public static final String CHANCE = "Chance";
	/**constant for Extra Yahtzee row*/
	public static final String EXTRA_YAHTZEE = "Extra Yahtzee";
	
	//copy attributes from class diagram
	private int aces;
	private int twos;
	private int threes;
	private int fours;
	private int fives;
	private int sixes;
	private int threeOfAKind;
	private int fourOfAKind;
	private int fullHouse;
	private int smallStraight;
	private int largeStraight;
	private int yahtzee;
	private int chance;
	private int extraYahtzee;
	
	private int upperOffset;
    private int lowerOffset;
        
	private ScorePanel scorePanel; //view
	
	/**
	* instantiates a scorecard, setting all values to zero
	*/
	private ScoreCard() {
		reset();
	}
	
	/**
	* returns the single instance of scorecard
	* @return singleton instance of scorecard
	*/
	public static ScoreCard getInstance() {
		if(scorecard == null) {
			scorecard = new ScoreCard();
		}
		
		return scorecard;
	}
	
	/**
	* resets all rows to zero
	*/
	public void reset() {
		aces = -1;
		twos = -1;
		threes = -1;
		fours = -1;
		fives = -1;
		sixes = -1;
		threeOfAKind = -1;
		fourOfAKind = -1;
		fullHouse = -1;
		smallStraight = -1;
		largeStraight = -1;
		yahtzee = -1;
		chance = -1;
		extraYahtzee = 0;
		upperOffset = 6;
		lowerOffset = 7;
	}
	
	/**
	* sets a row to the appropriate score given the dice
	* @param row row to fill out
	* @param dice dice to get score from
	*/
	public void set( String row, DiceSet dice ) {
		int[] dist;
		switch( row ) {
			case ACES:
				aces = dice.getDistribution()[0];    
				upperOffset--;
				break;
			case TWOS:
				twos = 2 * dice.getDistribution()[1];  
				upperOffset--;
				break;
			case THREES:
				threes = 3 * dice.getDistribution()[2];
				upperOffset--;
				break;
			case FOURS:
				fours = 4 * dice.getDistribution()[3];
				upperOffset--;
				break;
			case FIVES:
				fives = 5 * dice.getDistribution()[4];
				upperOffset--;
				break;
			case SIXES:
				sixes = 6 * dice.getDistribution()[5];
				upperOffset--;
				break;
			case THREE_OF_A_KIND:
				dist = dice.getDistribution();
				for( int i: dist ) {
					if( i >= 3 ) {
						threeOfAKind = dice.getSum();
					}
					break;
				}
				
				if( threeOfAKind == -1 ) {
					threeOfAKind = 0;
				}
				lowerOffset--;
				break;
			case FOUR_OF_A_KIND:
				dist = dice.getDistribution();
				for( int i: dist ) {
					if( i >= 3 ) {
						fourOfAKind = dice.getSum();
					}
					break;
				}
				
				if( fourOfAKind == -1 ) {
					fourOfAKind = 0;
				}
				lowerOffset--;
				break;
			case FULL_HOUSE:
				dist = dice.getDistribution();
				boolean has2 = false;
				boolean has3 = false;
				for( int i: dist ) {
					if( i == 3 ) {
						has3 = true;
					} else if( i == 2 ) {
						has2 = true;
					}
					
					if( has2 && has3 ) {
						fullHouse = 25;
						break;
					}
				}
				
				if( fullHouse == -1 ) {
					fullHouse = 0;
				}
				lowerOffset--;
				break;
			case SMALL_STRAIGHT:
				dist = dice.getDistribution();
				if( dist[0] >= 1 && dist[1] >= 1 && dist[2] >= 1 && dist[3] >= 1
					|| dist[4] >= 1 && dist[1] >= 1 && dist[2] >= 1 && 
					dist[3] >= 1 || dist[4] >= 1 && dist[5] >= 1 && dist[2] >= 1 
					&& dist[3] >= 1 ) {
					smallStraight = 30;	
				} else {
					smallStraight = 0;
				}
				lowerOffset--;
				break;
			case LARGE_STRAIGHT:
				dist = dice.getDistribution();
				if( dist[0] >= 1 && dist[1] >= 1 && dist[2] >= 1 && dist[3] >= 1
					&& dist[4] >= 1 || dist[5] >= 1 && dist[1] >= 1 && 
					dist[2] >= 1 && dist[3] >= 1 && dist[4] >= 1 ) {
					largeStraight = 40;
				} else {
					largeStraight = 0;
				}
				lowerOffset--;
				break;
			case YAHTZEE:
				dist = dice.getDistribution();
				for( int i: dist ) {
					if( i >= 3 ) {
						yahtzee = 50;
					}
					break;
				}
				
				if( yahtzee == -1 ) {
					yahtzee = 0;
				}
				lowerOffset--;
				break;
			case CHANCE:
				chance = dice.getSum();
				lowerOffset--;
				break;
			default:
		}
		scorePanel.setModel( this );
	}
	
	/**
	* fills out a row as a result of an extra Yahtzee
	* @param row row to fill out
	*/
	public void extraYahtzee(String row, DiceSet dice) {
            
		int total = dice.getSum();
		
		extraYahtzee += 100;
		
		switch( row ) {
			case ACES:
				aces = total;
				upperOffset--;
				break;
			case TWOS:
				twos = total;
				upperOffset--;
				break;
			case THREES:
				threes = total;
				upperOffset--;
				break;
			case FOURS:
				fours = total;
				upperOffset--;
				break;
			case FIVES:
				fives = total;
				upperOffset--;
				break;
			case SIXES:
				sixes = total;
				upperOffset--;
				break;
			case THREE_OF_A_KIND:
				threeOfAKind = total;
				lowerOffset--;
				break;
			case FOUR_OF_A_KIND:
				fourOfAKind = total;
				lowerOffset--;
				break;
			case FULL_HOUSE:
				fullHouse = 25;
				lowerOffset--;
				break;
			case SMALL_STRAIGHT:
				smallStraight = 30;
				lowerOffset--;
				break;
			case LARGE_STRAIGHT:
				largeStraight = 40;
				lowerOffset--;
				break;
			case CHANCE:
				chance = total;
				lowerOffset--;
				break;
			default:
		}
		scorePanel.setModel( this );
	}
	
	/**
	* returns the score of a given row
	* @param row row to return value of
	* @return value of desired row
	*/
	public int get( String row ) {
        switch( row ) {
			case ACES:
				return aces;
			case TWOS:
				return twos;
			case THREES:
				return threes;
			case FOURS:
				return fours;
			case FIVES:
				return fives;
			case SIXES:
				return sixes;
			case THREE_OF_A_KIND:
				return threeOfAKind;
			case FOUR_OF_A_KIND:
				return fourOfAKind;
			case FULL_HOUSE:
				return fullHouse;
			case SMALL_STRAIGHT:
				return smallStraight;
			case LARGE_STRAIGHT:
				return largeStraight;
			case YAHTZEE:
				return yahtzee;
			case CHANCE:
				return chance;
			case EXTRA_YAHTZEE:
				return extraYahtzee;
			default:
		}    
            
		return 0;
	}
	
	/**
	* returns the bonus score of this scorecard
	* @return 35 if the upper score is &gt;= 63 and 0 otherwise
	*/
	public int getBonus() {
            
		int upper = aces + twos + threes + fours + fives + sixes + upperOffset;
		
		if(upper >= 63) {
			return 35;
		}
		
		return 0;
	}
	
	/**
	* returns the total of the upper scorecard
	* @return total of upper scorecard
	*/
	public int getUpper() {
		return aces + twos + threes + fours + fives + sixes + upperOffset + 
				getBonus();
	}
	
	/**
	* returns the total of the lower scorecard
	* @return total of lower scorecard
	*/
	public int getLower() {
		return threeOfAKind + fourOfAKind + fullHouse + smallStraight 
				+ largeStraight + yahtzee + extraYahtzee + chance + lowerOffset;
	}
	
	/**
	* returns the overall score of this card
	* @return overall score of this card
	*/
	public int getTotal() {
		return getUpper() + getLower();
	}
	
	/**
	* registers the view representing this scorecard
	* @param view view to notify
	*/
	public void registerView( IView view ) {
		scorePanel = (ScorePanel)view;
	}
}
