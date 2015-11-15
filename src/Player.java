// Author: Mark Hadley
import java.util.ArrayList;

// Class that holds each player's cards (including community cards), balance, and evaluates
// its hand strength.
public class Player {
	ArrayList<Card> holeCards;
	int playerNumber;
	double balance;
	PokerHand bestHand;
	ArrayList<Card> cards;
	public Player(){
	}
	
	//constructor, no balance, defaults to 100.0
	public Player(int num) {
		playerNumber = num;
		balance = 100.0;
		cards = new ArrayList<Card>();
	}
	
	//constructor, with balance
	public Player (int num, double b){
		playerNumber = num;
		balance = b;
		cards = new ArrayList<Card>();
	}
	
	//adds the hole cards to the player
	public void addHoleCards(ArrayList<Card>h){
		holeCards = copyCards(h);
		this.addCards(h);
	}
	// helper function for copying cards (i.e. community cards)
	private ArrayList<Card> copyCards(ArrayList<Card> additional) {
		ArrayList<Card> returnList = new ArrayList<Card>();
		for (int i = 0; i < additional.size(); i++){
			returnList.add(additional.get(i));
		}
		return returnList;
	}
	
	//returns player's balance
	public double getBalance() {
		return balance;
		
	}
	
	//evaluates best hand for the player given its 7 cards.
	private void setBestHand(){
		if (cards.size() <= 5) 
			bestHand = new PokerHand(cards);
		else {
			for (int c1 = 0; c1 <= cards.size()-5; c1++){
				for (int c2 = c1+1; c2<= cards.size()-4; c2++){
					for (int c3 = c2+1; c3 <= cards.size()-3; c3++){
						for (int c4 = c3+1; c4 <=cards.size()-2; c4++){
							for (int c5 = c4+1; c5<=cards.size()-1; c5++){
								PokerHand newHand = new PokerHand(cards.get(c1),cards.get(c2),cards.get(c3),cards.get(c4),cards.get(c5));
								//System.out.println(newHand.toString());
								if (newHand.compareTo(bestHand)>0){
									bestHand = newHand;
									
								}
							}
						}
					}
				}
			}
			
		}
	}
	
	// returns best hand as a PokerHand object.
	public PokerHand getBestHand(){
		return bestHand;
	}
	
	// adds cards to the players hand, evaluates hand on each call.
	public void addCards(ArrayList<Card> moreCards) {
		cards.addAll(moreCards);
		this.setBestHand();
	}
	
	// returns player's current hand size.
	public int getCardCount(){
		return cards.size();
	}
	
	// toString method.
	@Override
	public String toString(){
		String returnString = new String();
		for (int i = 0; i < holeCards.size(); i++){
			returnString+=holeCards.get(i).toString()+" ";
		}
		return "Player "+playerNumber + ": $" + balance + " - "+returnString;
	}
	
	// return this Player's number for reference to the table.
	public int getPlayerNumber(){
		return playerNumber;
	}
	
	// sets the player's balance.
	public void setPlayerBalance(double b){
		balance = b;
	}
	
	// removes money from the player's balance
	public void takeMoney(double amt){
		balance -= amt;
	}
	
	// adds money to the player's balance
	public void giveMoney(double amt){
		balance += amt;
	}

	

}
