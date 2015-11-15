// Author: Mark Hadley
// A Class that represents the rank and suit of a playing card.
public class Card implements Comparable<Card> {
	private Rank rank;
	private Suit suit;

	public Card(Rank r, Suit s) {
		rank = r;
		suit = s;

	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;

	}

	@Override
	public int compareTo(Card c2){
		if (rank.getValue() > c2.getRank().getValue()){
			return 1;
		} else if (rank.getValue() < c2.getRank().getValue()){
			return -1;
			
		} else {
			return 0;
		}
	
	}
	
	
	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}
}
