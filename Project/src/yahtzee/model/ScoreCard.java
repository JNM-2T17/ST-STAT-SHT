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
            if(scorecard == null)
                scorecard = new ScoreCard();
            
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
		//use getDistribution to determine the score
		//see DiceSet documentation

                if(row.equals(ACES))
                {
                    aces = 1 * dice.getDistribution()[0];                    
                }
                else if(row.equals(TWOS))
                {
                    twos = 2 * dice.getDistribution()[1];  
                }
                else if(row.equals(THREES))
                {
                    threes = 3 * dice.getDistribution()[2];
                }
                else if(row.equals(FOURS))
                {
                    fours = 4 * dice.getDistribution()[3];
                }
                else if(row.equals(FIVES))
                {
                    fives = 5 * dice.getDistribution()[4];
                }
                else if(row.equals(SIXES))
                {
                     sixes = 6 * dice.getDistribution()[5];
                }
                else if(row.equals(THREE_OF_A_KIND))
                {
                    threeOfAKind = dice.getDistribution()[0] + 2 * dice.getDistribution()[1] + 3 * dice.getDistribution()[2]
                            + 4 * dice.getDistribution()[3] + 5 * dice.getDistribution()[4] + 6 * dice.getDistribution()[5];           
                }
                else if(row.equals(FOUR_OF_A_KIND))
                {
                    fourOfAKind = dice.getDistribution()[0] + 2 * dice.getDistribution()[1] + 3 * dice.getDistribution()[2]
                            + 4 * dice.getDistribution()[3] + 5 * dice.getDistribution()[4] + 6 * dice.getDistribution()[5];
                }
                else if(row.equals(FULL_HOUSE))
                {
                    fullHouse = 25;
                }
                else if(row.equals(SMALL_STRAIGHT))
                {
                    smallStraight = 30;
                }
                else if(row.equals(LARGE_STRAIGHT))
                {
                    largeStraight = 40;
                }
                else if(row.equals(YAHTZEE))
                {
                    if(yahtzee == 50)
                    {
                        extraYahtzee = 100;
                    }
                    else
                    {
                        yahtzee = 50;
                    }
                }
                else if(row.equals(CHANCE))
                {
                    chance = dice.getDistribution()[0] + 2 * dice.getDistribution()[1] + 3 * dice.getDistribution()[2]
                            + 4 * dice.getDistribution()[3] + 5 * dice.getDistribution()[4] + 6 * dice.getDistribution()[5];
                }
                
	}
	
	/**
	* fills out a row as a result of an extra Yahtzee
	* @param row row to fill out
	*/
	public void extraYahtzee(String row, DiceSet dice) {
            
            int total = dice.getDistribution()[0] + 2 * dice.getDistribution()[1] + 3 * dice.getDistribution()[2]
                            + 4 * dice.getDistribution()[3] + 5 * dice.getDistribution()[4] + 6 * dice.getDistribution()[5];
            
            if(row.equals(ACES))
            {
                aces = total;
            }
            else if(row.equals(TWOS))
            {
                twos = total;
            }
            else if(row.equals(THREES))
            {
                threes = total;
            }
            else if(row.equals(FOURS))
            {
                fours = total;
            }
            else if(row.equals(FIVES))
            {
                fives = total;
            }
            else if(row.equals(SIXES))
            {
                sixes = total;
            }
            else if(row.equals(THREE_OF_A_KIND))
            {
                threeOfAKind = total;
            }
            else if(row.equals(FOUR_OF_A_KIND))
            {
                fourOfAKind = total;
            }
            else if(row.equals(FULL_HOUSE))
            {
                fullHouse = 25;
            }
            else if(row.equals(SMALL_STRAIGHT))
            {
                smallStraight = 30;
            }
            else if(row.equals(LARGE_STRAIGHT))
            {
                largeStraight = 40;
            }
            else if(row.equals(CHANCE))
            {
                chance = total;
            }
	}
	
	/**
	* returns the score of a given row
	* @param row row to return value of
	* @return value of desired row
	*/
	public int get( String row ) {
            
            if(row.equals(ACES))
            {
                return aces;
            }
            else if(row.equals(TWOS))
            {
                return twos;
            }
            else if(row.equals(THREES))
            {
                return threes;
            }
            else if(row.equals(FOURS))
            {
                return fours;
            }
            else if(row.equals(FIVES))
            {
                return fives;
            }
            else if(row.equals(SIXES))
            {
                return sixes;
            }
            else if(row.equals(THREE_OF_A_KIND))
            {
                return threeOfAKind;
            }
            else if(row.equals(FOUR_OF_A_KIND))
            {
                return fourOfAKind;
            }
            else if(row.equals(FULL_HOUSE))
            {
                return fullHouse;
            }
            else if(row.equals(SMALL_STRAIGHT))
            {
                return smallStraight;
            }
            else if(row.equals(LARGE_STRAIGHT))
            {
                return largeStraight;
            }
            else if(row.equals(YAHTZEE))
            {
                return yahtzee;
            }
            else if(row.equals(CHANCE))
            {
                return chance;
            }
            
            return 0;
	}
	
	/**
	* returns the bonus score of this scorecard
	* @return 35 if the upper score is &gt;= 63 and 0 otherwise
	*/
	public int getBonus() {
            
            int upper = aces + twos + threes + fours + fives + sixes;
            
            if(upper >= 63)
            {
                return 35;
            }
            
		return 0;
	}
	
	/**
	* returns the total of the upper scorecard
	* @return total of upper scorecard
	*/
	public int getUpper() {
		return aces + twos + threes + fours + fives + sixes;
	}
	
	/**
	* returns the total of the lower scorecard
	* @return total of lower scorecard
	*/
	public int getLower() {
		return threeOfAKind + fourOfAKind + fullHouse + smallStraight +largeStraight + yahtzee 
                        + extraYahtzee + chance;
	}
	
	/**
	* returns the overall score of this card
	* @return overall score of this card
	*/
	public int getTotal() {
		return aces + twos + threes + fours + fives + sixes + threeOfAKind + fourOfAKind 
                        + fullHouse + smallStraight + largeStraight + yahtzee + extraYahtzee + chance;
	}
	
	/**
	* registers the view representing this scorecard
	* @param view view to notify
	*/
	public void registerView( IView view ) {
		scorePanel = (ScorePanel)view;
	}
}
