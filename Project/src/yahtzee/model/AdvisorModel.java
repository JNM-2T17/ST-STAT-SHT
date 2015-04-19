package yahtzee.model;

import java.util.Iterator;
import java.util.Scanner;


public class AdvisorModel {

	public static String calculateLargeStraightProbability(DiceSet diceSet, int rerollNo){
		String out = "The probability of rolling a Large Straight is ";
		
                int[] dist = diceSet.getDistribution();
                
                int[] dices = new int[6];
                    
                for(int i=0; i < 6; i++)
                {
                    if(dist[i] != 0)
                    dices[i]++;                     
                }
                
                if(dices[0] == 1 && dices[1] == 1 && dices[2] == 1 && dices[3] == 1 && dices[4] == 1)
                    return "Your rolled a Large Straight";
                if(dices[1] == 1 && dices[2] == 1 && dices[3] == 1 && dices[4] == 1 && dices[5] == 1)
                    return "Your rolled a Large Straight";
                
                if(dices[0] == 1 && dices[5] == 1)
                {
                    int former = dices[0] + dices[1] + dices[2];
                    int latter = dices[3] + dices[4] + dices[5];
                    
                    if(former < latter)
                    {
                        dices[0] = 0;
                    }
                    else
                    {
                        dices[5] = 0;
                    }
                    
                }
                String rerolling = "re-rolling dice: ";
                    
                int rerolls  = 0;
                int keepers = 0;
                    
                for(int i=0; i < 6; i++)
                {
                    //System.out.println(dices[i]);
                    int x = dist[i] - dices[i];
                    rerolls += x;
                    keepers += dices[i];
                     
                    while(x != 0)
                    {
                        rerolling += i+1 + " ";
                        x--;
                    }
                }
                
                
		if(rerollNo==1)
		{
                    
                    
		}
                
		else if(rerollNo==2)
		{
                    if(rerolls == 1)
                        out += "16.6667%. ";
                    else if(rerolls == 2)
                        out += "30.5556%. ";
                    else if(rerolls == 3)
                        out += "%. ";
                    else if(rerolls == 4)
                        out += "%. ";
                   
                   out += rerolling;
                    
                }
                
                return out;
	}
	
	public static String calculateSmallStraightProbability(DiceSet diceSet, int rerollNo){
		String out = "The probability of rolling a Small Straight is ";
		
                int[] dist = diceSet.getDistribution();
                
                int[] dices = new int[6];
                    
                for(int i=0; i < 6; i++)
                {
                    if(dist[i] != 0)
                    dices[i]++;                     
                }
                
                if(dices[0] == 1 && dices[1] == 1 && dices[2] == 1 && dices[3] == 1)
                    return "Your rolled a Small Straight";
                if(dices[1] == 1 && dices[2] == 1 && dices[3] == 1 && dices[4] == 1)
                    return "Your rolled a Small Straight";
                if(dices[2] == 1 && dices[3] == 1 && dices[4] == 1 && dices[5] == 1)
                    return "Your rolled a Small Straight";
                    
                    
                if(dices[0] == 1 && dices[5] == 1 || dices[1] == 1 && dices[5] == 1 || dices[0] == 1 && dices[4] == 1)
                {
                    int former = dices[0] + dices[1] + dices[2];
                    int latter = dices[3] + dices[4] + dices[5];
                        
                    if(former < latter)
                    {
                        dices[0] = 0;
                        dices[1] = 0;
                    }
                    else
                    {
                        dices[5] = 0;
                        dices[4] = 0;
                    }
                        
                }

                String rerolling = "re-rolling dice: ";
                    
                int rerolls  = 0;
                int keepers = 0;
                    
                for(int i=0; i < 6; i++)
                {
                    //System.out.println(dices[i]);
                    int x = dist[i] - dices[i];
                    rerolls += x;
                    keepers += dices[i];
                     
                    while(x != 0)
                    {
                        rerolling += i+1 + " ";
                        x--;
                    }
                }
                
                
		if(rerollNo==1)
		{
                    
                    
		}
                
		else if(rerollNo==2)
		{
                    if(rerolls == 1)
                        out += "16.6667%. ";
                    else if(rerolls == 2)
                        out += "30.5556%. ";
                    else if(rerolls == 3)
                        out += "%. ";
                    else if(rerolls == 4)
                        out += "%. ";
                                        
                    out += rerolling;
                    
                }
                
                return out;
	}
	
        public static String calculateYahtzeeProbability(DiceSet diceSet, int rerollNo){
        String out = "The probability of rolling a Yahtzee is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        for(int i=0; i<6; i++){
            if(dist[i]>max){
                max = dist[i];
                valueOfMaxDist = i+1;
            }
        }
        if(max==5) return "You rolled a Yahtzee!";
        if(rerollNo==1){
            if(max==1) return "The probability of rolling a Yahtzee is 0.8717%, re-rolling dice 2, 3, 4, 5";
            if(max==2){
                out += "2.8528% re-rolling dice ";
            }
            if(max==3){
                out += "9.3364% re-rolling dice ";
            }
            if(max==4){
                out += "30.5554% re-rolling dice ";
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2){
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            String val = ((double)1.0/Math.pow(6, 5-max)*100)+"";
            val = val.replace('.', ',');
            val = val.split(",")[0]+"."+val.split(",")[1].substring(0, 4);
            out += val+"%, re-rolling die/dice ";
            out += diceToReRoll;
        }
        return out;
    }

    
    public static String calculateFullHouseProbability(DiceSet diceSet, int rerollNo){
        String out = "The probability of rolling a Full House is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        int hasTwo = 0;
        int hasThree = 0;
        for(int i=0; i<6; i++){
            if(dist[i]>max){
                max = dist[i];
                valueOfMaxDist = i+1;
            }
            if(dist[i]==2) hasTwo++;
            if(dist[i]==3) hasThree++;
        }
        if(hasTwo==1 && hasThree==1) return "You rolled a Full House!";
        if(rerollNo==1){
            if(hasTwo==2){
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(dist[d.getValue()-1]==1) diceToReRoll += i+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                return out+"55.5556%, re-rolling die "+diceToReRoll;
            }
            else if(max==1){
                out += "7.5482%, re-rolling dice 2, 3, 4, 5";
                return out;
            } else if(max==2){
                out += "14.4676%, re-rolling dice ";
            } else if(max==3){
                out += "13.8889%, re-rolling dice ";
            } else{
                boolean extra = false;
                out += "25.8488%, re-rolling dice ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
                    else if(!extra){ diceToReRoll += i+", "; extra = true; }
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
                return out;
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2){
            if(hasTwo==2){
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(dist[d.getValue()-1]==1) diceToReRoll += i+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                return out+"33.3333%, re-rolling die "+diceToReRoll;
            }
            else if(max==1){
                out += "3.8580%, re-rolling dice 2, 3, 4, 5";
                return out;
            } else if(max==2){
                out += "9.2593%, re-rolling dice ";
            } else if(max==3){
                out += "13.8889%, re-rolling dice ";
            } else{
                boolean extra = false;
                out += "13.8889%, re-rolling dice ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
                    else if(!extra){ diceToReRoll += i+", "; extra = true; }
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
                return out;
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        return out;
    }
    
    public static String calculateThreeOfAKindProbability(DiceSet diceSet, int rerollNo){
        String out = "The probability of rolling Three of A Kind is ";
        int[] dist = diceSet.getDistribution();
        int max = 0;
        int valueOfMaxDist = 0;
        for(int i=0; i<6; i++){
            if(dist[i]>max){
                max = dist[i];
                valueOfMaxDist = i+1;
            }
        }
        if(max>=3){
            return "You rolled Three of A Kind!";
        } 
        if(rerollNo==1){
            if(max==1) return "The probability of rolling Three of A Kind is 35.8112%, re-rolling dice 2, 3, 4, 5";
            if(max==2){
                out+="66.5102%, re-rolling dice ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
            }
        }
        if(rerollNo==2){
            if(max==1){
                out+="13.1944%, re-rolling dice 2, 3, 4, 5";
            } else if(max==2){
                out+="42.1296%, re-rolling dice ";
                Iterator<Die> dice = diceSet.getDice();
                String diceToReRoll = "";
                for(int i=1; dice.hasNext(); i++){
                    Die d = dice.next();
                    if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
                }
                diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
                out += diceToReRoll;
            }
        }
        return out;
        
    }
    
    public static String calculateFourOfAKindProbability(DiceSet diceSet, int rerollNo){
        String out = "The probability of rolling Four of A Kind is ";
        if(rerollNo==1){
            int[] dist = diceSet.getDistribution();
            int max = 0;
            int valueOfMaxDist = 0;
            for(int i=0; i<6; i++){
                if(dist[i]>max){
                    max = dist[i];
                    valueOfMaxDist = i+1;
                }
            }
            if(max>=4){
                return "You rolled Four of A Kind!";
            } 
            if(max==1) return "The probability of rolling Four of A Kind is 8.7961%, re-rolling dice 2, 3, 4, 5";
            if(max==2){
                out+="22.3037%, re-rolling dice ";
            }
            if(max==3){
                out+="51.7747%, re-rolling dice ";
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        if(rerollNo==2){
            int[] dist = diceSet.getDistribution();
            int max = 0;
            int valueOfMaxDist = 0;
            for(int i=0; i<6; i++){
                if(dist[i]>max){
                    max = dist[i];
                    valueOfMaxDist = i+1;
                }
            }
            if(max>=4){
                return "You rolled Four of A Kind!";
            } else if(max==1){
                out+="1.6204%, re-rolling dice 2, 3, 4, 5";
                return out;
            } else if(max==2){
                out+="7.4074%, re-rolling dice ";
            } else if(max==3){
                out+="30.5556%, re-rolling dice ";
            }
            Iterator<Die> dice = diceSet.getDice();
            String diceToReRoll = "";
            for(int i=1; dice.hasNext(); i++){
                Die d = dice.next();
                if(d.getValue()!=valueOfMaxDist) diceToReRoll += i+", ";
            }
            diceToReRoll = diceToReRoll.substring(0, diceToReRoll.length()-2);
            out += diceToReRoll;
        }
        return out;
    }
        
    public static void main(String[] args){
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
    }

    public static String getProbabilities(DiceSet ds, int rerollNo){
        String out = "";
        out+=calculateThreeOfAKindProbability(ds, rerollNo)+"\n";
        out+=calculateFourOfAKindProbability(ds, rerollNo)+"\n";
        out+=calculateFullHouseProbability(ds, rerollNo)+"\n";
        out+=calculateSmallStraightProbability(ds, rerollNo)+"\n";
        out+=calculateLargeStraightProbability(ds, rerollNo)+"\n";
        out+=calculateYahtzeeProbability(ds, rerollNo)+"\n";
        return out;
    }
}
