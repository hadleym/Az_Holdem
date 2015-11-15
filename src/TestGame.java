//author: Mark Hadley
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class TestGame {

	@Test
	public void testDeck(){
		Deck deck = new Deck(5);
		assertEquals(52,deck.getSize());
		
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.addAll(deck.deal(1));
		assertEquals(3,hand.get(0).getRank().getValue());
		assertEquals(Suit.Hearts, hand.get(0).getSuit());
		assertEquals(51,deck.getSize());
		hand.addAll(deck.deal(1));
		assertEquals(50,deck.getSize());
	
		
		Deck deck2 = new Deck();
		assertEquals(52,deck2.getSize());
		//System.out.println(deck2.toString());
		
	}

	
	@Test
	public void testShuffle(){
		int rankHits = 0;
		int suitHits = 0;
		int cardHits = 0;
		int numOfTests = 10000;
		
		for (int i = 0 ; i < numOfTests; i++){
			
			boolean sameRank = false;
			boolean sameSuit = false;
			
			Deck deck1 = new Deck();
			Deck deck2 = new Deck(5);
		
			ArrayList<Card> hand1 = new ArrayList<Card>();
			hand1.addAll(deck1.deal(1));
			hand1.addAll(deck2.deal(1));
			
			if (hand1.get(0).getRank().getValue()== hand1.get(1).getRank().getValue()){
				sameRank = true;
				rankHits++;
			}
			
			if(hand1.get(0).getSuit().equals(hand1.get(1).getSuit())){
				sameSuit = true;
				suitHits++;
			}
			
			if (sameRank && sameSuit)
				cardHits++;
		}
		assertEquals((double)suitHits/numOfTests/.25, 1.0,.1);
		assertEquals((double)1/13/((double)rankHits/numOfTests), 1.0, .3);
		assertEquals((double)1/52, (double)cardHits/numOfTests,.01);
	
		
	}
	
	@Test
	public void TestPokerHandConstructor(){
		
		Deck deck = new Deck(5);
		PokerHand hand = new PokerHand(deck.deal(5));
		assertEquals(5,hand.getHand().size());
		
		
	}
	
	@Test
	public void testPlayerHandToStringAndConstructorAndGetBestHand(){
		Deck deck = new Deck();
		ArrayList<Card> dealtCards = deck.deal(5);
		PokerHand pokerHand = new PokerHand(dealtCards);
		Player p1 = new Player(1, 100);
		p1.addCards(dealtCards);
		assertEquals(100.0, p1.getBalance(),.00001);
		assertEquals(0,p1.getBestHand().compareTo(pokerHand));
		assertEquals(p1.getBestHand().toString(),pokerHand.toString());
	}
	
	@Test
	public void TestPlayerAddCards(){
		Deck deck = new Deck(5);
		ArrayList<Card> communityCards = deck.deal(5);
		
		Player p1 = new Player(1, 100);
		//System.out.println(p1.getBestHand().toString());
		assertEquals(0,p1.getCardCount());
		
		
		p1.addCards(communityCards);
		assertEquals(5,p1.getCardCount());
		
		assertEquals(0,p1.getBestHand().getTier());

	}


	@Test
	public void testWithInput(){
		String answer = new String();
		String str = "5 y y y y n";
		Scanner s = new Scanner(str);
		int numberOfPlayers = s.nextInt();
		System.out.print("How many players? ");
		System.out.println(numberOfPlayers+"\n");
		
		int numberOfHoleCards =2;
		int numberOfCommunityCards = 5;
		ArrayList<Player> players = new ArrayList<Player>();
		for (int index = 1; index <= numberOfPlayers; index++){
			players.add(new Player(index, 100.0));
		}
		do {
			
			Table table = new Table(players, numberOfHoleCards, numberOfCommunityCards);
			table.printCommunityCards();
			players = table.playGame();
			
			
			System.out.print("\nPlay another round? y or n: ");
			answer = s.next();
			System.out.println(answer+"\n");
		} while (answer.equals("y"));
		s.close();
		
	}
	

}
