package yahtzee.model;

import java.util.Iterator;
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
		this.rerollNo = diceSet.getRollNo();
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
			
		for(int i=0; i < 6; i++) {
			if(dist[i] != 0) {
				dice[i]++;                     
			}
		}
		
		if( dice[0] == 1 && dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && 
			dice[4] == 1) {
			return "You rolled a Large Straight";
		}
		
		if( dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && dice[4] == 1 && 
			dice[5] == 1) {
			return "You rolled a Large Straight";
		}
		
		if( dice[0] == 1 && dice[5] == 1 ) {
			int lower = dice[0] + dice[1] + dice[2];
			int upper = dice[3] + dice[4] + dice[5];
			
			if(lower < upper) {
				dice[0] = 0;
			} else {
				dice[5] = 0;
			}
			
		}
		
		String rerolling = "re-rolling dice ";
			
		int rerolls  = 0;
		
		for(int i=0; i < 6; i++) {
			//System.out.println(dice[i]);
			int x = dist[i] - dice[i];
			rerolls += x;
			 
			while(x != 0) {
				rerolling += rerolling.equals("re-rolling dice ") ? "" : ", ";
				rerolling += i + 1;
				x--;
			}
		}
		
		if(rerollNo==1) {
			if(rerolls == 1) {
				out += "30.5556%, "; // 1/6 + 5/6 * 1/6
			} else if(rerolls == 2) {
				out += "19.1358%, "; // 1/18 + 17/18 * 1/18 + 9/36 * 1/6 + 9/36 * 1/6 
			} else if(rerolls == 3) {
				out += "12.5%, ";      
			//6/216 + 210/216 * 6/216 + 3 (18/216 * 1/6) + 3(37/216 * 1/18)
			} else if(rerolls == 4) {
				out += "10.4338%, "; 
			}
			//24/1296 + 1272/1296 * 24/1296 + 4(65/1296 * 6/216) + 4(110/1296 * 1/18) + 4(84/1296 * 1/6)
		} else if(rerollNo==2) {
			if(rerolls == 1) {
				out += "16.6667%, "; //1/6
			} else if(rerolls == 2) {
				out += "5.5556%, "; // 1/18
			} else if(rerolls == 3) {
				out += "2.7778%, "; // 6/216
			} else if(rerolls == 4) {
				out += "1.8518%, "; //24/1296
			}
		}
		
		out += rerolling;
							
		return out;
	}
	
	public String calculateSmallStraightProbability() {
		String out = "The probability of rolling a Small Straight is ";
		
		int[] dist = diceSet.getDistribution();
		
		int[] dice = new int[6];
			
		for(int i=0; i < 6; i++)
		{
			if(dist[i] != 0)
			dice[i]++;                     
		}
		
		if(dice[0] == 1 && dice[1] == 1 && dice[2] == 1 && dice[3] == 1)
			return "You rolled a Small Straight";
		if(dice[1] == 1 && dice[2] == 1 && dice[3] == 1 && dice[4] == 1)
			return "You rolled a Small Straight";
		if(dice[2] == 1 && dice[3] == 1 && dice[4] == 1 && dice[5] == 1)
			return "You rolled a Small Straight";
			
			
		if(dice[0] == 1 && dice[5] == 1 || dice[1] == 1 && dice[5] == 1 
			|| dice[0] == 1 && dice[4] == 1) {
			int lower = dice[0] + dice[1] + dice[2];
			int upper = dice[3] + dice[4] + dice[5];
				
			if(lower < upper) {
				dice[0] = 0;
				dice[1] = 0;
			} else {
				dice[5] = 0;
				dice[4] = 0;
			}
		}

		String rerolling = "re-rolling dice ";
			
		int rerolls = 0;
			
		for(int i = 0; i < 6; i++) {
			//System.out.println(dice[i]);
			int x = dist[i] - dice[i];
			rerolls += x;
			
			while(x != 0) {
				rerolling += rerolling.equals("re-rolling dice ") ? "" : ", ";
				rerolling += i + 1;
				x--;
			}
		}
                
		if(rerollNo==1) {
			if(rerolls == 2)
				out += "30.5556%, "; // 11/36 + 25/36 * 11/36
			else if(rerolls == 3)
				out += "25.8487%, "; // 30/216 + 186/216 * 30/216
			else if(rerolls == 4)
				out += "15.9722%, "; // 108/1296 + 1188/1296 * 108/1296							
		} else if(rerollNo==2) {
			if(rerolls == 2)
				out += "30.5556%, "; // 11/36
			else if(rerolls == 3)
				out += "13.8889%, "; // 30/216
			else if(rerolls == 4)
				out += "8.3333%, "; // 108/1296
		}
		
		out += rerolling;
                
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
                Iterator<Die> dice = diceSet.getDice();
                out+="7.5482%, re-rolling dice with values "+dice.next().getValue()+", "+dice.next().getValue()+", "+
                                                                dice.next().getValue()+", "+dice.next().getValue();
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
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += (d.getValue())+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
            }
        }
        if(rerollNo==2) {
            if(max==1) {
                Iterator<Die> dice = diceSet.getDice();
                out+="13.1944%, re-rolling dice with values "+dice.next().getValue()+", "+dice.next().getValue()+", "+
                                                                dice.next().getValue()+", "+dice.next().getValue();
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
		if( rerollNo > 0 && rerollNo < 3 ) {
			String out = "";
			ScoreCard sc = ScoreCard.getInstance();
			if( sc.get( ScoreCard.THREE_OF_A_KIND ) == -1 ) {
				out += calculateThreeOfAKindProbability() + ".\n";
			}
			
			if( sc.get( ScoreCard.FOUR_OF_A_KIND ) == -1 ) {
				out += calculateFourOfAKindProbability() + ".\n";
			}
			
			if( sc.get( ScoreCard.FULL_HOUSE ) == -1 ) {
				out += calculateFullHouseProbability() + ".\n";
			}
			
			if( sc.get( ScoreCard.SMALL_STRAIGHT ) == -1 ) {
				out += calculateSmallStraightProbability() + ".\n";
			}
			
			if( sc.get( ScoreCard.LARGE_STRAIGHT ) == -1 ) {
				out += calculateLargeStraightProbability() + ".\n";
			}
			
			if( sc.get( ScoreCard.YAHTZEE ) == -1 ) {
				out += calculateYahtzeeProbability() + ".";
			}
			
			return out;
		} else {
			return "";
		}
    }
	
	public void registerView( IView view ) {
		this.view = view;
	}
}
