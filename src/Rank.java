// author: Mark Hadley
// An enum which represents the 13 ranks of a playing card
public enum Rank {

	Deuce, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

	int getValue() {

		switch (this) {

		case Deuce:
			return 2;

		case Three:
			return 3;

		case Four:
			return 4;

		case Five:
			return 5;

		case Six:
			return 6;

		case Seven:
			return 7;

		case Eight:
			return 8;

		case Nine:
			return 9;

		case Ten:
			return 10;

		case Jack:
			return 11;

		case Queen:
			return 12;

		case King:
			return 13;

		case Ace:
			return 14;

		default:
			return 0;
		}
	}

	@Override
	public String toString() {
		switch (this) {

		case Deuce:
			return "2";

		case Three:
			return "3";

		case Four:
			return "4";

		case Five:
			return "5";

		case Six:
			return "6";

		case Seven:
			return "7";

		case Eight:
			return "8";

		case Nine:
			return "9";

		case Ten:
			return "10";

		case Jack:
			return "J";

		case Queen:
			return "Q";

		case King:
			return "K";

		case Ace:
			return "A";

		default:
			return "";

		}

	}

}