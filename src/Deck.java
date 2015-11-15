import java.util.ArrayList;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck;
	 Random generator;
	 int seed;
	 
	public Deck(){
		deck = new ArrayList<Card>();
		this.loadCards();
		generator = new Random();
		shuffle();
	
	}
	
	public Deck(int seed){
		deck = new ArrayList<Card>();
		this.loadCards();
		generator = new Random(seed);
		shuffle();
	}
	
	public int getSize(){
		return deck.size();
	}
	
	public ArrayList<Card> deal(int cardsToDeal) {
		ArrayList<Card> returnCards = new ArrayList<Card>();
		if (cardsToDeal > this.getSize()) {
			System.out.print("Out of Cards!");
			return null;
		}
		for(int i = 0; i < cardsToDeal ; i++){
			returnCards.add(deck.get(0));
			deck.remove(0);
		}
		return returnCards;
	}
	
	private void shuffle(){
		for (int i = 0; i < 500; i++){
			int randomInt1 = generator.nextInt(this.getSize());
			int randomInt2 = generator.nextInt(this.getSize());
			Card temp = deck.get(randomInt1);
			deck.set(randomInt1, deck.get(randomInt2));
			deck.set(randomInt2, temp);
		}
	}
	
//	@Override
//	public String toString(){
//		String returnString = new String();
//		for (int i = 0; i < this.getSize(); i++){
//			returnString+=deck.get(i).toString()+"\n";
//		}
//		return returnString;
//	}
//	
	
	private void loadCards(){
		for (int i = 0; i < 52; i++){
			int suitValue = i%4;
			int rankValue = i%13;
			Rank rank;
			Suit suit;
			if (suitValue == 0) 
				suit = Suit.Clubs;
			else if (suitValue == 1)
				suit = Suit.Diamonds;
			else if (suitValue == 2)
				suit = Suit.Hearts;
			else
				suit = Suit.Spades;
			
			if(rankValue == 0)
				rank = Rank.Deuce;
			else if(rankValue == 1)
				rank = Rank.Three;
			else if(rankValue == 2)
				rank = Rank.Four;
			else if(rankValue == 3)
				rank = Rank.Five;
			else if(rankValue == 4)
				rank = Rank.Six;
			else if(rankValue == 5)
				rank = Rank.Seven;
			else if(rankValue == 6)
				rank = Rank.Eight;
			else if(rankValue == 7)
				rank = Rank.Nine;
			else if(rankValue == 8)
				rank = Rank.Ten;
			else if(rankValue == 9)
				rank = Rank.Jack;
			else if(rankValue == 10)
				rank = Rank.Queen;
			else if(rankValue == 11)
				rank = Rank.King;
			else
				rank = Rank.Ace;		
			deck.add(new Card(rank, suit));
		}
	}

}
