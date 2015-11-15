//Author: Mark Hadley
// an enum that represents the 4 suits of a playing card.
public enum Suit {
	Clubs, Diamonds, Hearts, Spades;

	@Override
	public String toString() {
		switch (this) {
		case Clubs:
			
			return "" + '\u2663';
		case Diamonds:
			return "" + '\u2666';
		case Hearts:
			return "" + '\u2665';
		case Spades:
			return "" + '\u2660';
		default:
			return "" + ' ';
		}
	}
}
