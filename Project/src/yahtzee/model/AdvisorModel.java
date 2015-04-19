package yahtzee.model;

import java.util.Iterator;
import java.util.Scanner;
import java.text.DecimalFormat;
import yahtzee.view.IView;

public class AdvisorModel {
	private DiceSet diceSet;
	private int rerollNo;
	private IView view;
	
	public AdvisorModel( DiceSet diceSet ) {
		setDiceSet( diceSet );
		rerollNo = 0;
	}
	
	public DiceSet getDiceSet() {
		return diceSet;
	}
	
	public void setDiceSet( DiceSet diceSet ) {
		this.diceSet = diceSet;
		if( view != null ) {
			view.setModel( this );
		}
	}
	
	public int getRerollNo( int rerollNo ) {
		return rerollNo;
	}
	
	public void setRerollNo( int rerollNo ) 
		throws IllegalArgumentException {
		if( rerollNo >= 0 || rerollNo <= 2 ) {
			this.rerollNo = rerollNo;
			if( view != null ) {
				view.setModel( this );
			}
		} else {
			throw new IllegalArgumentException("Reroll No must be 0, 1, or 2");
		}
	}
	
	public String calculateLargeStraightProbability() {
		String out = "The probability of rolling a Large Straight is ";
		
		int[] dist = diceSet.getDistribution();
		
		int[] dice = new int[6];
		
		//mark the values that have a 1
		for(int i=0; i < 6; i++) {
			if(dist[i] != 0)
			dice[i]++;                     
		}
		
		if(dice[0] == 1 && dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && dice[4] == 1) {
			return "You rolled a Large Straight";
		}
		if(dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && dice[4] == 1 && dice[5] == 1) {
			return "You rolled a Large Straight";
		}
			
		//if there is a 1 and a 6
		if(dice[0] == 1 && dice[5] == 1) {
			int former = dice[0] + dice[1] + dice[2]; //sum of 1, 2, 3
			int latter = dice[3] + dice[4] + dice[5]; //sum of 4, 5, 6
			
			//more low dice
			if(former < latter) {
				dice[0] = 0; //consider less
			} else { //more high dice
				dice[5] = 0; //consider less
			}
		}
		String rerolling = "re-rolling dice with values ";
			
		int rerolls  = 0;
			
		for(int i=0; i < 6; i++) {
			//System.out.println(dice[i]);
			int x = dist[i] - dice[i];
			rerolls += x;
			 
			while(x != 0) {
				rerolling += rerolling.equals("re-rolling dice with values ") 
								? " " : ", ";
				rerolling += i+1;
				x--;
			}
		}
                
                
		if(rerollNo==1) {
                    
                    
		} else if(rerollNo==2) {
			if(rerolls == 1) {
				out += "16.6667%. ";
			} else if(rerolls == 2) {
				out += "30.5556%. ";
			} else if(rerolls == 3) {
				out += "%. ";
			} else if(rerolls == 4) {
				out += "%. ";
			}
		   
			out += rerolling;
		}
                
		return out;
	}
	
	public String calculateSmallStraightProbability() {
		String out = "The probability of rolling a Small Straight is ";
		
		int[] dist = diceSet.getDistribution();
		
		int[] dice = new int[6];
			
		for(int i=0; i < 6; i++) {
			if(dist[i] != 0)
			dice[i]++;                     
		}
		
		if(dice[0] == 1 && dice[1] == 1 && dice[2] == 1 && dice[3] == 1) {
			return "You rolled a Small Straight";
		}
		if(dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && dice[4] == 1) {
			return "You rolled a Small Straight";
		}
		if(dice[2] == 1 && dice[3] == 1 && dice[4] == 1 && dice[5] == 1) {
			return "You rolled a Small Straight";
		}
			
			
		if(dice[0] == 1 && dice[5] == 1 || dice[1] == 1 && dice[5] == 1 || dice[0] == 1 && dice[4] == 1) {
			int former = dice[0] + dice[1] + dice[2];
			int latter = dice[3] + dice[4] + dice[5];
				
			if(former < latter) {
				dice[0] = 0;
				dice[1] = 0;
			} else {
				dice[5] = 0;
				dice[4] = 0;
			}
				
		}

		String rerolling = "re-rolling dice with values ";
			
		int rerolls = 0;
		int keepers = 0;
			
		for(int i=0; i < 6; i++) {
			//System.out.println(dice[i]);
			int x = dist[i] - dice[i];
			rerolls += x;
			keepers += dice[i];
			 
			while(x != 0) {
				rerolling += rerolling.equals("re-rolling dice with values ") 
								? " " : ", ";
				rerolling += i + 1;
				x--;
			}
		}
		
		
		if(rerollNo==1) {
                    
                    
		} else if(rerollNo==2) {
			if(rerolls == 1) {
				out += "16.6667%. ";
			} else if(rerolls == 2) {
				out += "30.5556%. ";
			} else if(rerolls == 3) {
				out += "%. ";
			} else if(rerolls == 4) {
				out += "%. ";
			}
								
			out += rerolling;
		}
                
		return out;
	}
	
	public String calculateYahtzeeProbability() {
        String out = "The probability of rolling a Yahtzee is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        for(int i=0; i<6; i++) {
            if(dist[i]>max) {
                max = dist[i];
                valueOfMaxDist = i+1;
            }
        }
		
        if(max==5) {
			return "You rolled a Yahtzee!";
		}
        if(rerollNo==1) {
            if(max==1) {
				out += "0.8717%, re-rolling dice with values ";
				for( int i = 1; i < 5; i++ ) {
					out += i <= 1 ? "" : ", ";
					out += diceSet.getDie(i).getValue();
				}
			}
            if(max==2) {
                out += "2.8528% re-rolling dice with values ";
            }
            if(max==3) {
                out += "9.3364% re-rolling dice with values ";
            }
            if(max==4) {
                out += "30.5554% re-rolling dice with values ";
            }
			
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            
			for(int i=1; dice.hasNext(); i++) {
                Die d = dice.next();
                if( d.getValue() != valueOfMaxDist ) {
					diceToReRoll += d.getValue() + ", ";
				}
            }
			
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2) {
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
			boolean plural = false;
            for(int i=1; dice.hasNext(); i++) {
                Die d = dice.next();
                if( d.getValue()!= valueOfMaxDist ) {
					if( !diceToReRoll.equals("") ) {
						plural = true;
					}
					diceToReRoll += d.getValue() + ", ";
				}
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits( 4 );
            df.setMinimumFractionDigits( 4 );
            String val = df.format(((double)1.0/Math.pow(6, 5-max)*100));
            out += val + "%, re-rolling " + (plural ? "dice" : "die") +  
						" with values ";
            out += diceToReRoll;
        }
        return out;
    }

    
    public String calculateFullHouseProbability() {
        String out = "The probability of rolling a Full House is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        int hasTwo = 0;
        int hasThree = 0;
        for(int i=0; i<6; i++) {
            if(dist[i]>max){
                max = dist[i];
                valueOfMaxDist = i+1;
            }
            if(dist[i]==2) hasTwo++;
            if(dist[i]==3) hasThree++;
        }
		
        if(hasTwo==1 && hasThree==1) {
			return "You rolled a Full House!";
		}
        
		if(rerollNo==1) {
            if(hasTwo==2) {
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(dist[d.getValue()-1]==1) {
						diceToReRoll += d.getValue() + ", ";
					}
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                return out + "55.5556%, re-rolling die with value " + 
						diceToReRoll;
            } else if(max==1) {
                out += "7.5482%, re-rolling dice with values 2, 3, 4, 5";
                return out;
            } else if(max==2) {
                out += "14.4676%, re-rolling dice with values ";
            } else if(max==3) {
                out += "13.8889%, re-rolling dice with values ";
            } else {
                boolean extra = false;
                out += "25.8488%, re-rolling dice with values ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) {
						diceToReRoll += d.getValue() + ", ";
					} else if(!extra){ 
						diceToReRoll += d.getValue() + ", "; 
						extra = true; 
					}
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
                return out;
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++) {
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) {
					diceToReRoll += d.getValue() + ", ";
				}
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2) {
            if(hasTwo==2) {
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(dist[d.getValue()-1] == 1) {
						diceToReRoll += d.getValue() + ", ";
					}
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                return out + "33.3333%, re-rolling die with value " + 
						diceToReRoll;
            } else if(max==1) {
                out += "3.8580%, re-rolling dice with values ";
				for( int i = 1; i < 5; i++ ) {
					out += i <= 1 ? "" : ", ";
					out += diceSet.getDie(i).getValue();
				}
                return out;
            } else if(max==2) {
                out += "9.2593%, re-rolling dice with values ";
            } else if(max==3) {
                out += "13.8889%, re-rolling dice with values ";
            } else {
                boolean extra = false;
                out += "13.8889%, re-rolling dice with values ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) {
						diceToReRoll += d.getValue() + ", ";
					}
                    else if(!extra) { 
						diceToReRoll += d.getValue() + ", "; 
						extra = true; 
					}
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
                return out;
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++) {
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) {
					diceToReRoll += d.getValue() + ", ";
				}
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        return out;
    }
    
    public String calculateThreeOfAKindProbability() {
        String out = "The probability of rolling Three of A Kind is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        for(int i=0; i<6; i++) {
            if(dist[i]>max) {
                max = dist[i];
                valueOfMaxDist = i+1;
            }
        }
        if(max>=3) {
            return "You rolled Three of A Kind!";
        } 
        if(rerollNo==1) {
            if(max==1) {
				out += "35.8112%, re-rolling dice with values ";
				for( int i = 1; i < 5; i++ ) {
					out += i <= 1 ? "" : ", ";
					out += diceSet.getDie(i).getValue();
				}
			}
            if(max==2) {
                out+="66.5102%, re-rolling dice with values ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
            }
        }
        if(rerollNo==2) {
            if(max==1) {
                out+="13.1944%, re-rolling dice with values 2, 3, 4, 5";
            } else if(max==2) {
                out+="42.1296%, re-rolling dice with values ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++) {
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) {
					diceToReRoll += d.getValue() + ", ";
				}
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
            }
        }
        return out;
        
    }
    
    public String calculateFourOfAKindProbability() {
        String out = "The probability of rolling Four of A Kind is ";
        if(rerollNo==1) {
            int[] dist = diceSet.getDistribution();
            int max = 0;
            int valueOfMaxDist = 0;
            for(int i=0; i<6; i++) {
                if(dist[i]>max) {
                    max = dist[i];
                    valueOfMaxDist = i+1;
                }
            }
            if(max>=4) {
                return "You rolled Four of A Kind!";
            } 
            if(max==1) {
				out += "8.7961%, re-rolling dice with values ";
				for( int i = 1; i < 5; i++ ) {
					out += i <= 1 ? "" : ", ";
					out += diceSet.getDie(i).getValue();
				}
			}
            if(max==2) {
                out+="22.3037%, re-rolling dice with values ";
            }
            if(max==3) {
                out+="51.7747%, re-rolling dice with values ";
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++) {
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) {
					diceToReRoll += d.getValue() + ", ";
				}
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2) {
            int[] dist = diceSet.getDistribution();
            int max = 0;
            int valueOfMaxDist = 0;
            for(int i=0; i<6; i++) {
                if(dist[i]>max){
                    max = dist[i];
                    valueOfMaxDist = i+1;
                }
            }
            if(max>=4) {
                return "You rolled Four of A Kind!";
            } else if(max==1) {
                out+="1.6204%, re-rolling dice with values ";
				for( int i = 1; i < 5; i++ ) {
					out += i <= 1 ? "" : ", ";
					out += diceSet.getDie(i).getValue();
				}
            } else if(max==2) {
                out+="7.4074%, re-rolling dice with values ";
            } else if(max==3) {
                out+="30.5556%, re-rolling dice with values ";
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) {
					diceToReRoll += d.getValue() + ", ";
				}
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        return out;
    }
        
    /*public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter dice values");
            DiceSet ds = new DiceSet();
            int[] values = new int[5];
            for(int i=0; i<5; i++) values[i] = sc.nextInt();
            ds.setDice(values);
            System.out.println("reroll #1 " + calculateLargeStraightProbability(ds,1));
            System.out.println("reroll #2 " + calculateLargeStraightProbability(ds,2));
            System.out.println("reroll #1 " + calculateSmallStraightProbability(ds,1));
            System.out.println("reroll #2 " + calculateSmallStraightProbability(ds,2));
            System.out.println(calculateYahtzeeProbability(ds, 1));
            System.out.println(calculateYahtzeeProbability(ds, 2));
            System.out.println(calculateFullHouseProbability(ds, 1));
            System.out.println(calculateFullHouseProbability(ds, 2));
            System.out.println(calculateThreeOfAKindProbability(ds, 1));
            System.out.println(calculateThreeOfAKindProbability(ds, 2));
            System.out.println(calculateFourOfAKindProbability(ds, 1));
            System.out.println(calculateFourOfAKindProbability(ds, 2));
            System.out.println("");
        }
    }*/

    public String toString() {
        String out = "";
        out += calculateThreeOfAKindProbability() + "\n";
        out += calculateFourOfAKindProbability() + "\n";
        out += calculateFullHouseProbability() + "\n";
        out += calculateSmallStraightProbability() + "\n";
        out += calculateLargeStraightProbability() + "\n";
        out += calculateYahtzeeProbability() + "\n";
        return out;
    }
	
	public void registerView( IView view ) {
		this.view = view;
	}
}
