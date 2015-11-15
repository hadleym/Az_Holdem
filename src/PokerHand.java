// Author: Mark Hadley

import java.util.ArrayList;
import java.util.Collections;

// a class which handles and compares a 5 card poker hand.
// throws errors if another card of equal rank and equal
// suit is encountered.
public class PokerHand implements Comparable<PokerHand> {
	private ArrayList<Card> hand;
	private int tier;
	static final int MIN_SIZE = 5;
	
	//Constructor which takes in 5 Cards.
	//tests for duplicates within hand.
	//assigns "Tier" which translates to hand strength.
	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
		hand = new ArrayList<Card>();
		hand.add(c1);
		hand.add(c2);
		hand.add(c3);
		hand.add(c4);
		hand.add(c5);
		//if (containsDuplicate(hand)) throw (new DuplicateCardException());
		tier = setTier();
	}
	
	//test if the ArrayList contains duplicate values
//	private boolean containsDuplicate(ArrayList<Card> a) {
//		for (int i = 0; i < a.size(); i++){
//			for (int j = i+1; j< a.size(); j++){
//				if (a.get(i).toString().compareTo(a.get(j).toString())==0){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	public PokerHand(ArrayList<Card> deal) {
		hand = new ArrayList<Card>();
		hand.addAll(deal);
		tier = setTier();
	}

	// asserts whether the two hands contain all unique cards, then 
	// compares the Tier of the two given hands.  If the tier value is equal
	// then it compares the two hands sequentially, depending on what type
	// of tier it is.
	@Override
	public int compareTo(PokerHand player2) {
//		ArrayList<Card> bothHands = new ArrayList<Card>();
//		for (int i = 0; i < hand.size(); i++){
//			bothHands.add(hand.get(i));
//			bothHands.add(player2.getHand().get(i));
//		}
		//if (containsDuplicate(bothHands)) throw (new DuplicateCardException());
		if (this.getTier() == -1 && player2.getTier()==-1) return 0;
		if (this.getTier()> player2.getTier()){
			return 1;
		} else if (this.getTier() < player2.getTier()){
			return -1;
		} else {
			
			if (this.getTier() == 0 ||
					this.getTier() == 1 || 
					this.getTier()== 2 ||
					this.getTier() == 5 || 
					this.getTier()==7 ||
					this.getTier() == 3 ){
				return compareCards(5, player2);
			} else if (this.getTier() == 4 || this.getTier() == 8){
				return compareCards(1, player2);
			} else if (this.getTier() == 6 )
				return compareCards(2, player2);
			
			return 0;
		}
	}

	// sets the 'Tier' of the card from 0 (high card, weak) to 8 (straight flush, strong)
	private int setTier() {
		
		tier = 0;
		sortHand();
		if (hand.size() < MIN_SIZE) return -1;
		if(containsFlush()){
			if (containsStraight(0) || (hand.get(0).getRank()==Rank.Ace && containsStraight(1))){
				return 8;
			} else {
				return 5;
			}
		}else if (containsFourOfAKind()){
			return 7;
			
		}else if (cointainsFullHouse()){
			return 6;

		}else if (containsStraight(0) || (hand.get(0).getRank()==Rank.Ace && containsStraight(1))){
			return 4;
		}else if (containsThreeOfAKind(0)){
			return 3;
		} else if (containsPair(0) && containsPair(2)){ // 2 PAIR
			return 2;
		} else if (containsPair(0)){ // PAIR
			return 1;
		} else {
			return 0;
		}
		
	}

	// If the hand contains 4 cards of equal rank, sorts a single card of that 
	// rank to the front of the hand and returns true.
	private boolean containsFourOfAKind() {
		if (hand.get(0).compareTo(hand.get(1))==0 
				&& hand.get(1).compareTo(hand.get(2))==0
				&& hand.get(2).compareTo(hand.get(3))==0){
			return true;
		}
		if (hand.get(1).compareTo(hand.get(2))==0 
				&& hand.get(2).compareTo(hand.get(3))==0
				&& hand.get(3).compareTo(hand.get(4))==0){
			return true;
		}
		return false;
	}
    
	// if the hand contains a full house, if the -of-a-kind portion of the 
	// hand is a lower rank than the non-3-of-a-kind portion, sorts a single card of 
	// the lower rank to the front of the hand, and then returns true.
	private boolean cointainsFullHouse() {
		if (hand.get(0).compareTo(hand.get(1))==0 
			&& hand.get(1).compareTo(hand.get(2))==0
			&& hand.get(3).compareTo(hand.get(4))==0){
				Card temp = hand.get(3);
				hand.set(3,hand.get(1));
				hand.set(1,temp);
			return true;
		}
		if (hand.get(0).compareTo(hand.get(1))==0 
				&& hand.get(2).compareTo(hand.get(3))==0
				&& hand.get(3).compareTo(hand.get(4))==0){
				//swap hand.get(2) with hand(0);
				Card temp = hand.get(2);
				hand.set(2,hand.get(0));
				hand.set(0,temp);
				return true;
			}
		return false;
	}
	
	// returns true if the hand contains 5 cards of equal suit.
	private boolean containsFlush() {
		for (int i = 0; i < hand.size()-1; i++){
			Suit c1 = hand.get(i).getSuit();
			Suit c2 = hand.get(i+1).getSuit();
			if (c1 != c2) {
				return false;
			}
		}
		return true;
	}
    // returns true if the hand, from index int start to hand.size()-1 
	// are of consecutive rank.
	private boolean containsStraight(int start) {
		for (int i = start; i < hand.size()-1; i++){
			int c1 = hand.get(i).getRank().getValue();
			int c2 = hand.get(i+1).getRank().getValue();
			if (c2+1 != c1) {
				return false;
			}
		}
		if(start!=0){
			//swap hand(0) with hand(1)
			Card temp = hand.get(1);
			hand.set(1,hand.get(0));
			hand.set(0,temp);
			
		}
		return true;
	}
	
	// returns true if 3 cards are of equal rank
	private boolean containsThreeOfAKind(int i) {
		if (containsPair(0) && cardExistsToRightExclusive(1,hand.size())){
			return true;
		}
		return false;
	}
	
	// returns true if two cards, starting from index int i, are of equal rank.
	// if yes, then the two cards are moved to the i and i+1 index of the hand
	private boolean containsPair(int i) {
		for (int frontIndex = i; frontIndex < hand.size(); frontIndex++){
			for (int secondIndex = frontIndex+1; secondIndex < hand.size(); secondIndex++ ){
				int comparedValue = hand.get(frontIndex).compareTo(hand.get(secondIndex));
				if (comparedValue == 0){ //match found @ frontIndex && secondIndex
					//swap frontIndex with index0;
					moveAndShiftLeft(frontIndex,i);
					//swap secondIndex with index1;
					moveAndShiftLeft(secondIndex,i+1);
					return true;
				}
			}
		}
		return false;
	}

	// returns a reference to the ArrayList<Card> hand
	public ArrayList<Card> getHand() {
		return hand;
	}


	// returns the tier of the hand
	public int getTier() {
		return tier;
	}
    // sorts the hand by highest to lowest rank, with the highest rank at the lowest index
	// of the hand ArrayList
	private void sortHand() {
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i; j < hand.size(); j++) {
				if (hand.get(i).getRank().getValue() < hand.get(j).getRank().getValue()) {
					Card temp = hand.get(i);
					hand.set(i, hand.get(j));
					hand.set(j, temp);
				}
			}
		}
	}
	
	//swaps card at index end to index start and moves all following cards down in index
	// should be called 'ShiftRight'
	private void moveAndShiftLeft(int end, int start){
		Card temp = hand.get(end);
		for (int i = end; i>start; i--){
			hand.set(i,hand.get(i-1));
		}
		hand.set(start, temp);
	}
	
	// returns true if the card at index start equals the rank of at 
	// least 1 card to the right
	private boolean cardExistsToRightExclusive(int start, int end){

		for (int i = start+1; i < end; i++){
			if (hand.get(start).compareTo(hand.get(i)) == 0) {
				return true;
			}
		}
		return false;
	}
	
	// compares the first n cards of two poker hands.
	// returns the result of the first instance of two cards of the
	// same index that do not have the same rank.
	// if all compared cards have equal rank at their respective idicies,
	// return 0.
	private int compareCards(int n, PokerHand player2){
			
			for (int i = 0; i <n; i ++){
				int p1 = hand.get(i).getRank().getValue();
				int p2 = player2.getHand().get(i).getRank().getValue();
				
				if (p1 > p2) {
					return 1;
				} else if (p1 < p2) {
					return -1;
				}
			}
			return 0;
	}
	
	@Override
	public String toString(){
		ArrayList<Card> temp = new ArrayList<Card>();
		String returnString = new String();
		for (int i = 0; i < hand.size(); i++){
			temp.add(hand.get(i));
			
		}
		Collections.sort(temp);
		for (int i = 0; i < hand.size(); i++){
			returnString = returnString+temp.get(i).toString()+" ";
		}
		return returnString;
	}

	public String toStringTier(){
		if (tier < 0)
			return " ";
		else if (tier == 0)
			return "High Card";
		else if (tier == 1)
			return "Pair";
		else if (tier==2)
			return "Two Pair";
		else if (tier == 3) 
			return "Three of a Kind";
		else if (tier == 4)
			return "Straight";
		else if (tier == 5) 
			return "Flush";
		else if (tier == 6)
			return "Full House";
		else if (tier == 7)
			return "Four of a Kind";
		else 
			return "Straight Flush";
		
	}

}
