// Author: Mark Hadley
// Test cases for Class PokerHand
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PokerHandTest {
	Card C2 = new Card(Rank.Deuce, Suit.Clubs);
	Card C3 = new Card(Rank.Three, Suit.Clubs);
	Card C4 = new Card(Rank.Four, Suit.Clubs);
	Card C5 = new Card(Rank.Five, Suit.Clubs);
	Card C6 = new Card(Rank.Six, Suit.Clubs);
	Card C7 = new Card(Rank.Seven, Suit.Clubs);
	Card C8 = new Card(Rank.Eight, Suit.Clubs);
	Card C9 = new Card(Rank.Nine, Suit.Clubs);
	Card C10 = new Card(Rank.Ten, Suit.Clubs);
	Card CJ = new Card(Rank.Jack, Suit.Clubs);
	Card CQ = new Card(Rank.Queen, Suit.Clubs);
	Card CK = new Card(Rank.King, Suit.Clubs);
	Card CA = new Card(Rank.Ace, Suit.Clubs);

	Card D2 = new Card(Rank.Deuce, Suit.Diamonds);
	Card D3 = new Card(Rank.Three, Suit.Diamonds);
	Card D4 = new Card(Rank.Four, Suit.Diamonds);
	Card D5 = new Card(Rank.Five, Suit.Diamonds);
	Card D6 = new Card(Rank.Six, Suit.Diamonds);
	Card D7 = new Card(Rank.Seven, Suit.Diamonds);
	Card D8 = new Card(Rank.Eight, Suit.Diamonds);
	Card D9 = new Card(Rank.Nine, Suit.Diamonds);
	Card D10 = new Card(Rank.Ten, Suit.Diamonds);
	Card DJ = new Card(Rank.Jack, Suit.Diamonds);
	Card DQ = new Card(Rank.Queen, Suit.Diamonds);
	Card DK = new Card(Rank.King, Suit.Diamonds);
	Card DA = new Card(Rank.Ace, Suit.Diamonds);

	Card H2 = new Card(Rank.Deuce, Suit.Hearts);
	Card H3 = new Card(Rank.Three, Suit.Hearts);
	Card H4 = new Card(Rank.Four, Suit.Hearts);
	Card H5 = new Card(Rank.Five, Suit.Hearts);
	Card H6 = new Card(Rank.Six, Suit.Hearts);
	Card H7 = new Card(Rank.Seven, Suit.Hearts);
	Card H8 = new Card(Rank.Eight, Suit.Hearts);
	Card H9 = new Card(Rank.Nine, Suit.Hearts);
	Card H10 = new Card(Rank.Ten, Suit.Hearts);
	Card HJ = new Card(Rank.Jack, Suit.Hearts);
	Card HQ = new Card(Rank.Queen, Suit.Hearts);
	Card HK = new Card(Rank.King, Suit.Hearts);
	Card HA = new Card(Rank.Ace, Suit.Hearts);

	Card S2 = new Card(Rank.Deuce, Suit.Spades);
	Card S3 = new Card(Rank.Three, Suit.Spades);
	Card S4 = new Card(Rank.Four, Suit.Spades);
	Card S5 = new Card(Rank.Five, Suit.Spades);
	Card S6 = new Card(Rank.Six, Suit.Spades);
	Card S7 = new Card(Rank.Seven, Suit.Spades);
	Card S8 = new Card(Rank.Eight, Suit.Spades);
	Card S9 = new Card(Rank.Nine, Suit.Spades);
	Card S10 = new Card(Rank.Ten, Suit.Spades);
	Card SJ = new Card(Rank.Jack, Suit.Spades);
	Card SQ = new Card(Rank.Queen, Suit.Spades);
	Card SK = new Card(Rank.King, Suit.Spades);
	Card SA = new Card(Rank.Ace, Suit.Spades);

	@Test
	public void testConstructor() {
		PokerHand pairDeuce = new PokerHand(C2, H2, CA, CK, CQ);
		assertEquals(Rank.Deuce, pairDeuce.getHand().get(0).getRank());
		assertEquals(Rank.Deuce, pairDeuce.getHand().get(1).getRank());
		assertEquals(Rank.Ace, pairDeuce.getHand().get(2).getRank());
		assertEquals(Rank.King, pairDeuce.getHand().get(3).getRank());
		assertEquals(Rank.Queen, pairDeuce.getHand().get(4).getRank());
	}

	@Test
	public void testAceHigh() {
		PokerHand aceHigh = new PokerHand(C3, H2, CA, CK, CQ);
		assertEquals(Rank.Deuce, aceHigh.getHand().get(4).getRank());
		assertEquals(Rank.Three, aceHigh.getHand().get(3).getRank());
		assertEquals(Rank.Ace, aceHigh.getHand().get(0).getRank());
		assertEquals(Rank.King, aceHigh.getHand().get(1).getRank());
		assertEquals(Rank.Queen, aceHigh.getHand().get(2).getRank());
	}

	@Test
	public void testDeucePair() {
		PokerHand deucePair = new PokerHand(C10, H5, C2, S2, C8);
		assertEquals(1, deucePair.getTier());
		assertEquals(Rank.Deuce, deucePair.getHand().get(0).getRank());
		assertTrue(S2.compareTo(deucePair.getHand().get(1)) == 0);
		assertTrue(C10.compareTo(deucePair.getHand().get(2)) == 0);
		assertTrue(C8.compareTo(deucePair.getHand().get(3)) == 0);
		assertTrue(H5.compareTo(deucePair.getHand().get(4)) == 0);

	}

	@Test
	public void testTwoPair() {
		PokerHand twoPairKingsQueens = new PokerHand(CK, HQ, HK, SQ, C8);
		assertEquals(2, twoPairKingsQueens.getTier());
		assertEquals(Rank.King, twoPairKingsQueens.getHand().get(0).getRank());
		assertTrue(CK.compareTo(twoPairKingsQueens.getHand().get(0)) == 0);
		assertTrue(HK.compareTo(twoPairKingsQueens.getHand().get(1)) == 0);
		assertTrue(HQ.compareTo(twoPairKingsQueens.getHand().get(2)) == 0);
		assertTrue(SQ.compareTo(twoPairKingsQueens.getHand().get(3)) == 0);
		assertTrue(C8.compareTo(twoPairKingsQueens.getHand().get(4)) == 0);

	}

	@Test
	public void testThreeOfKind() {
		PokerHand threeKings = new PokerHand(CK, HK, SK, SQ, C8);
		assertEquals(3, threeKings.getTier());
		assertTrue(CK.compareTo(threeKings.getHand().get(0)) == 0);
		assertTrue(HK.compareTo(threeKings.getHand().get(1)) == 0);
		assertTrue(SK.compareTo(threeKings.getHand().get(2)) == 0);

		PokerHand threeDeuces = new PokerHand(CK, HQ, C2, D2, S2);
		assertEquals(3, threeDeuces.getTier());
		assertTrue(C2.compareTo(threeDeuces.getHand().get(0)) == 0);
		assertTrue(D2.compareTo(threeDeuces.getHand().get(1)) == 0);
		assertTrue(CK.compareTo(threeDeuces.getHand().get(2)) == 0);
		assertTrue(HQ.compareTo(threeDeuces.getHand().get(3)) == 0);
		assertTrue(S2.compareTo(threeDeuces.getHand().get(4)) == 0);

	}

	@Test
	public void testStraight() {
		PokerHand sevenHighStraight = new PokerHand(C6, D5, C7, H4, H3);
		assertEquals(4, sevenHighStraight.getTier());
		assertTrue(sevenHighStraight.getHand().get(0).compareTo(C7) == 0);

		PokerHand fiveHighStraight = new PokerHand(C3, C2, CA, D5, D4);
		assertEquals(4, fiveHighStraight.getTier());
		assertTrue(fiveHighStraight.getHand().get(0).compareTo(D5) == 0);

	}

	@Test
	public void testFlush() {
		PokerHand queenHighFlush = new PokerHand(C3, C2, CQ, C4, C8);
		assertEquals(5, queenHighFlush.getTier());
		PokerHand fiveHighStraighFlush = new PokerHand(H3, H2, H5, HA, H4);
		assertEquals(8, fiveHighStraighFlush.getTier());
	}

	@Test
	public void testFullHouse() {
		PokerHand threesFullTwos = new PokerHand(C3, H3, D3, H2, D2);
		PokerHand twosFullThrees = new PokerHand(C2, H3, D3, H2, D2);
		assertEquals(6, threesFullTwos.getTier());

		assertEquals(6, twosFullThrees.getTier());
		assertTrue(twosFullThrees.getHand().get(0).compareTo(C2) == 0);
	}

	@Test
	public void testFourOfAKind() {
		PokerHand fourFours = new PokerHand(C4, C5, D4, S4, H4);
		assertEquals(7, fourFours.getTier());
		assertTrue(fourFours.getHand().get(0).compareTo(C4) == 0);
		PokerHand fourEights = new PokerHand(D2, D8, C8, H8, S8);
		assertEquals(7, fourEights.getTier());
		assertTrue(fourEights.getHand().get(0).compareTo(D8) == 0);
	}

	@Test
	public void testStraightFlush() {
		PokerHand straightFlushKingHigh = new PokerHand(HQ, HK, HJ, H10, H9);
		assertEquals(8, straightFlushKingHigh.getTier());
		assertTrue(straightFlushKingHigh.getHand().get(0).compareTo(HK) == 0);
		PokerHand fiveHighStraightFlush = new PokerHand(H3, H2, H5, HA, H4);
		assertEquals(8, fiveHighStraightFlush.getTier());
		assertTrue(fiveHighStraightFlush.getHand().get(0).compareTo(H5) == 0);
	}

	@Test
	public void testComparePairVersusHighCard() {
		PokerHand aceHigh = new PokerHand(C3, H2, CA, CK, CQ);
		PokerHand deucePair = new PokerHand(C10, H5, C2, S2, C8);
		assertTrue(aceHigh.compareTo(deucePair) < 0);
	}

	@Test
	public void compareManyHands() {
		PokerHand aceHigh = new PokerHand(C3, H2, CA, DK, CQ);
		PokerHand deucePair = new PokerHand(C10, H5, C2, S2, C8);
		PokerHand twoPairKingsQueens = new PokerHand(CK, HQ, HK, SQ, C4);
		PokerHand threeJacks = new PokerHand(CJ, HJ, SJ, DQ, D8);
		PokerHand sevenHighStraight = new PokerHand(C6, D5, C7, H4, H3);
		ArrayList<PokerHand> manyHands = new ArrayList<PokerHand>();
		manyHands.add(threeJacks);
		manyHands.add(sevenHighStraight);
		manyHands.add(aceHigh);
		manyHands.add(twoPairKingsQueens);
		manyHands.add(deucePair);
		for (int i = 0; i < manyHands.size(); i++) {
			for (int j = i+1; j < manyHands.size(); j++) {
				if (manyHands.get(i).compareTo(manyHands.get(j)) > 0) {
					PokerHand temp = manyHands.get(j);
					manyHands.set(j, manyHands.get(i));
					manyHands.set(i, temp);
				}
			}
		}
		assertTrue(aceHigh.compareTo(deucePair) < 0);
		assertTrue(manyHands.get(0).compareTo(manyHands.get(1)) < 0);
	}

	@Test
	public void compareSimilarHandsHighCard() {
		PokerHand aceHighKingQueen = new PokerHand(C3, H2, CA, CK, CQ);
		PokerHand aceHighKingJack = new PokerHand(S3, D2, SA, DK, CJ);
		assertTrue(aceHighKingQueen.compareTo(aceHighKingJack) > 0);
	}

	@Test
	public void compareSimilarHandsPair() {
		PokerHand deucePairTenEightKicker = new PokerHand(C10, H5, C2, S2, C8);
		PokerHand decuePairTenSevenKicker = new PokerHand(D10, S5, D2, H2, C7);
		assertTrue(deucePairTenEightKicker.compareTo(decuePairTenSevenKicker) > 0);
	}

	@Test
	public void compareSimilarHandsTwoPair() {
		PokerHand deucePairTenPairEightKicker = new PokerHand(C10, H10, C2, S2, C8);
		PokerHand decuePairTenPairSevenKicker = new PokerHand(D10, S10, D2, H2, C7);
		assertTrue(deucePairTenPairEightKicker.compareTo(decuePairTenPairSevenKicker) > 0);
	}

	@Test
	public void compareSimilarHandsThreeOfAKind() {
		PokerHand threeKings = new PokerHand(CK, HK, SK, C2, C8);
		PokerHand threeQueens = new PokerHand(DQ, HQ, SQ, D2, D8);
		assertTrue(threeKings.compareTo(threeQueens) > 0);
	}

	@Test
	public void compareSimilarHandsStraight() {
		PokerHand straightKingHigh = new PokerHand(H9, CK, SQ, SJ, S10);
		PokerHand straightFiveHigh = new PokerHand(HA, C5, C4, C3, C2);
		assertTrue(straightKingHigh.compareTo(straightFiveHigh) > 0);
	}

	@Test
	public void compareSimilarFlush() {
		PokerHand flush1 = new PokerHand(H10, H8, H6, H4, HA);
		PokerHand flush2 = new PokerHand(S10, S8, S6, S4, SA);
		PokerHand flushWeak = new PokerHand(D10, D8, D5, D4, DA);
		assertTrue(flush1.compareTo(flush2) == 0);
		assertTrue(flush1.compareTo(flushWeak) > 0);
	}

	@Test
	public void compareSimilarFullHouse() {
		PokerHand threesFullTwos = new PokerHand(C3, S3, H3, S2, C2);
		PokerHand eightsFullTwos = new PokerHand(C8, H8, D2, H2, D8);
		assertTrue(eightsFullTwos.compareTo(threesFullTwos) > 0);

	}

	@Test
	public void testSimilarFourOfAKind() {
		PokerHand fourFours = new PokerHand(C4, C5, D4, S4, H4);
		PokerHand fourEights = new PokerHand(D2, D8, C8, H8, S8);
		assertTrue(fourFours.compareTo(fourEights) < 0);

	}

	@Test
	public void testSimilarStraightFlush() {
		PokerHand straightFlushKingHigh = new PokerHand(HQ, HK, HJ, H10, H9);
		PokerHand fiveHighStraightFlush = new PokerHand(H3, H2, H5, HA, H4);
		PokerHand fiveHighStraightFlushClubs = new PokerHand(C3, C2, C5, CA, C4);
		assertTrue(fiveHighStraightFlush.compareTo(straightFlushKingHigh) < 0);
		assertTrue(fiveHighStraightFlushClubs.compareTo(fiveHighStraightFlush) == 0);

	}

	
	@Test
	public void testHandLessThan5Cards(){
		ArrayList<Card> cards= new ArrayList<Card>();
		cards.add(HQ);
		cards.add(HJ);
		
		
		PokerHand twoCards = new PokerHand(cards);
		assertEquals(2, twoCards.getHand().size());
		assertEquals(-1, twoCards.getTier());
		
		
	}
	
	@Test
	public void twoPairAgain(){
		PokerHand jacksandsevens = new PokerHand(HJ, SJ, S9, D7, C7);
		PokerHand sevensandthrees = new PokerHand(H3,C3,D7,C7,SJ);
		assertEquals(-1, sevensandthrees.compareTo(jacksandsevens));
	}
	
	@Test
	public void twoFullHouse(){
		PokerHand aceFullJacks= new PokerHand(HJ, SJ, CA, DA, HA);
		PokerHand aceFullEights= new PokerHand(H8, S8, CA, DA, HA);
		assertEquals(-1,aceFullEights.compareTo(aceFullJacks));
	}
	
	@Test
	public void twoFourOfAKind(){
		PokerHand aceKingKicker= new PokerHand(DA, HA, CA, SA, SK);
		PokerHand aceQueenKicker= new PokerHand(DA, HA, CA, SA, SQ);
		assertEquals(1,aceKingKicker.compareTo(aceQueenKicker));
	}
	
	@Test
	public void twoThreeOfAKind(){
		PokerHand aceKingKicker= new PokerHand(DA, HA, CA, S2, SK);
		PokerHand aceQueenKicker= new PokerHand(DA, HA, CA, S2, SQ);
		assertEquals(1,aceKingKicker.compareTo(aceQueenKicker));
	}
}
