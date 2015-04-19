/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.model;

import java.util.HashMap;
import java.util.Map;
import yahtzee.view.IView;
import yahtzee.view.ScorePanel;

/**
 *
 * @author Marc San Pedro
 */
public class PatternFinder {
    private static PatternFinder instance = null;
    private DiceSet diceSet;
    private ScorePanel scorePanel;
    
    private PatternFinder() {
        diceSet = new DiceSet();
    }
    
	public static PatternFinder getInstance() {
		if( instance == null ) {
			instance = new PatternFinder();
		}
		
		return instance;
	}
	
    public void setDice(DiceSet ds){
        this.diceSet = ds;
		scorePanel.setModel( this );
    }
    
    public Map<String, Integer> getUpper(){
        Map<String, Integer> upper = new HashMap<String, Integer>();
        int[] distribution = diceSet.getDistribution();
        
		upper.put(ScoreCard.ACES, distribution[0]);
        upper.put(ScoreCard.TWOS, distribution[1] * 2);
        upper.put(ScoreCard.THREES, distribution[2] * 3);
        upper.put(ScoreCard.FOURS, distribution[3] * 4);
        upper.put(ScoreCard.FIVES, distribution[4] * 5);
        upper.put(ScoreCard.SIXES, distribution[5] * 6);
        
		return upper;
    }
    
    public Map<String, Integer> getLower(){
        Map<String, Integer> lower = new HashMap<String, Integer>();
        int[] distribCount = new int[6];
        int[] distribution = diceSet.getDistribution();
        int sum = diceSet.getSum();
        
        for(int i=0; i<distribution.length; i++){
            distribCount[distribution[i]]++;
        }
        
        boolean hasSS = false;
        boolean hasLS = false;
        if( distribution[0] == 1 && distribution[1] == 1 && distribution[2] == 1
				&& distribution[3] == 1 ) {
                hasSS = true;
            if( distribution[4] == 1 ) {
                hasLS = true;
			}
        } else if( distribution[1] == 1 && distribution[2] == 1 
					&& distribution[3] == 1 && distribution[4] == 1 ){
            hasSS = true;
            if( distribution[0] == 1 || distribution[5] == 1 ) {
                hasLS = true;
			}
        } else if( distribution[2] == 1 && distribution[3] == 1 && 
					distribution[4] == 1 && distribution[5] == 1 ) {
            hasSS = true;
            if( distribution[1] == 1 ) {
                hasLS = true;
			}
        }
        
        if( distribCount[3] == 1 ) {
            lower.put(ScoreCard.THREE_OF_A_KIND, sum);
		} else {
			lower.put(ScoreCard.THREE_OF_A_KIND, 0 );
		}
		
        if( distribCount[4] == 1 ) {
            lower.put(ScoreCard.FOUR_OF_A_KIND, sum);
		} else {
			lower.put(ScoreCard.FOUR_OF_A_KIND, 0 );
		}
		
		if( distribCount[2] == 1 && distribCount[3] == 1 ) {
            lower.put(ScoreCard.FULL_HOUSE, 25);
		} else {
			lower.put(ScoreCard.FULL_HOUSE, 0 );
		}
		
        if(hasSS) {
            lower.put(ScoreCard.SMALL_STRAIGHT, 30);
		}
		
        if(hasLS) {
            lower.put(ScoreCard.LARGE_STRAIGHT, 40);
		}
		
        if( distribCount[5] == 1 ) {
            lower.put(ScoreCard.YAHTZEE, 50);
		} else {
			lower.put(ScoreCard.YAHTZEE, 0 );
		}
		
        lower.put(ScoreCard.CHANCE, sum);
        
		return lower;
    }
    
    /**
    * registers the view representing this scorecard
    * @param view view to notify
    */
    public void registerView( IView view ) {
		scorePanel = (ScorePanel)view;
    }
}
