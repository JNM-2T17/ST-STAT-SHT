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
	
	//copy attributes from class diagram
	private int aces = 0;
        private int twos = 0;
        private int threes = 0;
        private int fours = 0;
        private int fives = 0;
        private int sixes = 0;
        private int threeOfAKind = 0;
        private int fourOfAKind = 0;
        private int fullHouse = 0;
        private int smallStraight = 0;
        private int largeStraight = 0;
        private int yahtzee = 0;
        private int chance = 0;
        private int extraYahtzee = 0;
        
	private ScorePanel scorePanel; //view
	
	/**
	* instantiates a scorecard, setting all values to zero
	*/
	private ScoreCard() {
            scorecard = new ScoreCard();
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
		aces = 0;
		twos = 0;
		threes = 0;
		fours = 0;
		fives = 0;
		sixes = 0;
		threeOfAKind = 0;
		fourOfAKind = 0;
		fullHouse = 0;
		smallStraight = 0;
		largeStraight = 0;
		yahtzee = 0;
		chance = 0;
		extraYahtzee = 0;
	}
	
	/**
	* sets a row to the appropriate score given the dice
	* @param row row to fill out
	* @param dice dice to get score from
	*/
	public void set( String row, DiceSet dice ) {
		switch( row ) {
			case ACES:
				aces = dice.getDistribution()[0];    
				break;
			case TWOS:
				twos = 2 * dice.getDistribution()[1];  
				break;
			case THREES:
				threes = 3 * dice.getDistribution()[2];
				break;
			case FOURS:
				fours = 4 * dice.getDistribution()[3];
				break;
			case FIVES:
				fives = 5 * dice.getDistribution()[4];
				break;
			case SIXES:
				sixes = 6 * dice.getDistribution()[5];
				break;
			case THREE_OF_A_KIND:
				threeOfAKind = dice.getDistribution()[0] + 2 
								* dice.getDistribution()[1] + 3 
								* dice.getDistribution()[2] + 4
								* dice.getDistribution()[3] + 5
								* dice.getDistribution()[4] + 6
								* dice.getDistribution()[5];          
				break;
			case FOUR_OF_A_KIND:
				fourOfAKind = dice.getDistribution()[0] + 2 
								* dice.getDistribution()[1] + 3 
								* dice.getDistribution()[2] + 4
								* dice.getDistribution()[3] + 5
								* dice.getDistribution()[4] + 6
								* dice.getDistribution()[5];
				break;
			case FULL_HOUSE:
				fullHouse = 25;
				break;
			case SMALL_STRAIGHT:
				smallStraight = 30;
				break;
			case LARGE_STRAIGHT:
				largeStraight = 40;
				break;
			case YAHTZEE:
				if(yahtzee == 50) {
					extraYahtzee += 100;
				} else {
					yahtzee = 50;
				}
				break;
			case CHANCE:
				chance = dice.getDistribution()[0] + 2 
							* dice.getDistribution()[1] + 3 
							* dice.getDistribution()[2] + 4
							* dice.getDistribution()[3] + 5
							* dice.getDistribution()[4] + 6
							* dice.getDistribution()[5];
				break;
			default:
		}
	}
	
	/**
	* fills out a row as a result of an extra Yahtzee
	* @param row row to fill out
	*/
	public void extraYahtzee(String row, DiceSet dice) {
            
		int total = dice.getDistribution()[0] + 2 * dice.getDistribution()[1] 
						+ 3 * dice.getDistribution()[2] + 4 
						* dice.getDistribution()[3] + 5 
						* dice.getDistribution()[4] + 6 
						* dice.getDistribution()[5];
		
		switch( row ) {
			case ACES:
				aces = total;
				break;
			case TWOS:
				twos = total;
				break;
			case THREES:
				threes = total;
				break;
			case FOURS:
				fours = total;
				break;
			case FIVES:
				fives = total;
				break;
			case SIXES:
				sixes = total;
				break;
			case THREE_OF_A_KIND:
				threeOfAKind = total;
				break;
			case FOUR_OF_A_KIND:
				fourOfAKind = total;
				break;
			case FULL_HOUSE:
				fullHouse = 25;
				break;
			case SMALL_STRAIGHT:
				smallStraight = 30;
				break;
			case LARGE_STRAIGHT:
				largeStraight = 40;
				break;
			case CHANCE:
				chance = total;
				break;
			default:
		}
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
			default:
		}    
            
		return 0;
	}
	
	/**
	* returns the bonus score of this scorecard
	* @return 35 if the upper score is &gt;= 63 and 0 otherwise
	*/
	public int getBonus() {
            
		int upper = aces + twos + threes + fours + fives + sixes;
		
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
		return aces + twos + threes + fours + fives + sixes + getBonus();
	}
	
	/**
	* returns the total of the lower scorecard
	* @return total of lower scorecard
	*/
	public int getLower() {
		return threeOfAKind + fourOfAKind + fullHouse + smallStraight 
				+ largeStraight + yahtzee + extraYahtzee + chance;
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
