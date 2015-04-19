package yahtzee.model;

import java.util.Arrays;
import java.util.Iterator;

import yahtzee.view.IView;

/**
* This class represents a set of dice
* @author Austin Fernandez
*/
public class DiceSet {
	private Die[] dice; //dice 
	private boolean[] keepers; //array of values of dice to not roll
	private int rollNo;
	private IView gamePanel; //view
	
	/**
	* basic constructor; randomizes dice
	*/
	public DiceSet() {
		initComponents(); //initialize components
	}
	
	/**
	* constructor that sets initial dice values
	* @param diceValues values of dice
	* @throws IllegalArgumentException when the number of values is not five
	*/
	public DiceSet( int[] diceValues, int rollNo )
		throws IllegalArgumentException {
		initComponents(); //initlaize components
		
		setRollNo( rollNo );
		setDice( diceValues ); //set values
	}
	
	/**
	* initializes attributes
	*/
	private void initComponents() {
		//initialize arrays
		dice = new Die[5];
		keepers = new boolean[5];
		
		//initialize array values
		for( int i = 0; i < 5; i++ ) {
			dice[i] = new Die();
			keepers[i] = false; //no keepers yet
		}
	}
	
	/**
	* returns whether it is the 1st, 2nd, or 3rd roll
	* @return whether it is the 1st, 2nd, or 3rd roll
	*/
	public int getRollNo() {
		return rollNo;
	}
	
	/**
	* sets whether it is the 1st, 2nd, or 3rd roll
	* @param rollNo roll number
	*/
	public void setRollNo( int rollNo ) {
		this.rollNo = rollNo;
	}
	
	/**
	* sets a die's value
	* @param dieIndex index of die [0,5] 
	* @param dieValue new value of die [1,6]
	* @throws IllegalArgumentException when the number of values is not five
	* @throws ArrayIndexOutOfBoundsException if index is outside [0,5]
	*/
	public void setDie( int dieIndex, int dieValue )
		throws IllegalArgumentException, 
			   ArrayIndexOutOfBoundsException {
		dice[dieIndex].setValue(dieValue);
		
		if( gamePanel != null ) {
			gamePanel.setModel(this); //update view with new die values
		}
	}
	
	/**
	* sets die values
	* @param diceValues values of dice
	* @throws IllegalArgumentException if number of dice values is not equal to 
	*									five
	*/
	public void setDice( int[] diceValues ) 
		throws IllegalArgumentException {
		if( diceValues.length != 5 ) {
			throw new IllegalArgumentException( diceValues.length < 5 ? 
											    "Not enough dice values" : 
												"Too many dice values" );
		}
		
		for( int i = 0; i < 5; i++ ) {
			dice[i].setValue(diceValues[i]); //set each value
		}
		
		if( gamePanel != null ) {
			gamePanel.setModel(this); //update view with new die values
		}
		
		PatternFinder.getInstance().setDice( this );
	}
	
	/**
	* returns a die
	* @param index index of die
	* @return chosen die
	* @throws ArrayIndexOutOfBoundsException if index is outside [0,5]
	*/
	public Die getDie( int index ) 
		throws ArrayIndexOutOfBoundsException {
		return dice[index];
	}
	
	/**
	* returns all the dice
	* @return iterator of dice
	*/
	public Iterator<Die> getDice() {
		return Arrays.asList(dice).iterator();
	}
	
	/**
	* returns an array of six integers, each representing the quantity of dice 
	* showing a certain value
	* e.g. if the roll is 4, 3, 4, 4, 6, the method would return 
	*      0, 0, 1, 3, 0, 1 since there are no 1's, 2's, and 5's; there is one
	*      3 and one six, and there are three fours
	* @return distribution of dice
	*/
	public int[] getDistribution() {
		int[] dist = new int[6]; //new array of six integers
		
		//initialize to zero
		for( int i = 0; i < 6; i++ ) {
			dist[i] = 0;
		}
		
		for( Die d: dice ) {
			dist[d.getValue() - 1]++; //increment the appropriate index for each 
		}							  //die value
		
		return dist;
	}
	
	/**
	* sets dice to roll
	* @param keepers array of boolean values: true means the dice will be kept, 
	*                false means the dice will be rolled
	* @throws IllegalArgumentException when the number of values is not five
	*/
	public void setKeepers( boolean[] keepers ) 
		throws IllegalArgumentException {
		if( keepers.length != 5 ) {
			throw new IllegalArgumentException( keepers.length < 5 ? 
											    "Not enough keeper values" : 
												"Too many keeper values" );
		} else {
			for( int i = 0; i < 5; i++ ) {
				this.keepers[i] = keepers[i];
			}
		}
	}
	
	/**
	* rolls all non-kept dice
	*/
	public void roll() {
		for( int i = 0; i < 5; i++ ) {
			if( !keepers[i] ) { //if not to be kept
				dice[i].roll(); //roll die
			}
		}
		
		if( gamePanel != null ) {
			gamePanel.setModel(this); //update view with new die values
		}
		
		PatternFinder.getInstance().setDice( this );
	}
	
	/**
	* returns the sum of these dice
	* @return the sum of these dice
	*/
	public int getSum() {
		int sum = 0;
		
		for( Die die: dice ) {
			sum += die.getValue();
		}
		
		return sum;
	}
	
	/**
	* registers a view
	* @param gamePanel view to register
	*/
	public void registerView( IView gamePanel ) {
		this.gamePanel = gamePanel;
	}
	
	/**
	* returns the values of the dice as a string
	* @return values of the dice as a string
	*/
	public String toString() {
		String s = "";
		
		for( int i = 0; i < 5; i++ ) {
			s += "" + dice[i].getValue() + ( i < 4 ? "\t" : "" );
		}
		return s;
	}
}