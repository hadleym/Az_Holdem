
// Author: Mark Hadley
import java.util.ArrayList;

// class that handles player objects and the community cards ArrayList<Cards>
// also keeps track of the winner(s).
public class Table {
	ArrayList<Player> winner;
	static final int SEPARATOR_LENGTH = 40;
	ArrayList<Player> players;
	ArrayList<Card> communityCards;
	Deck deck;
	double kitty;

	// constructor
	// accepts an ArrayList<Player> from which it can pull balances, deal cards
	// to and give and
	// take money from.
	public Table(ArrayList<Player> p, int numberOfHoleCards, int numberOfCommunityCards) {
		deck = new Deck(); // set seed
		kitty = 0;
		players = new ArrayList<Player>();
		if (deck.getSize() < numberOfHoleCards * p.size() + numberOfCommunityCards) {
			System.out.println("not enough cards to play a game");
			System.exit(1);
		}
		communityCards = deck.deal(numberOfCommunityCards);

		// add players in with their hole cards
		for (int index = 0; index < p.size(); index++) {
			Player p1 = new Player(p.get(index).getPlayerNumber(), p.get(index).getBalance());
			p1.addCards(communityCards);
			p1.addHoleCards(deck.deal(numberOfHoleCards));
			players.add(p1);
		}
	}

	// plays a single hand of poker with the ArrayList<Players>
	// prints output
	public ArrayList<Player> playGame() {

		if (players.size() < 2) {
			System.out.println("Not enough players");
			System.exit(1);
		}

		// takes the ante from each player
		for (int i = 0; i < players.size(); i++) {
			players.get(i).takeMoney(2.0);
			kitty += 2.0;
		}
		this.printPlayers();

		// gets winner(s)
		winner = new ArrayList<Player>();
		winner.add(players.get(0));
		for (int i = 1; i < players.size(); i++) {
			if (winner.get(0).getBestHand().compareTo(players.get(i).getBestHand()) < 0) {
				winner = new ArrayList<Player>();
				winner.add(players.get(i));
			} else if (winner.get(0).getBestHand().compareTo(players.get(i).getBestHand()) == 0) {
				winner.add(players.get(i));
			}
		}

		this.printWinners();
		return players;

	}

	// prints the winner(s) and their hands.
	private void printWinners() {
		if (winner.size() > 1) {
			System.out.println("Winning hands (tie)");
			this.printSeparator('c', SEPARATOR_LENGTH);
			for (int i = 0; i < winner.size(); i++) {
				winner.get(i).setPlayerBalance(winner.get(i).getBalance() + (kitty / winner.size()));
				System.out.println(
						winner.get(i).getBestHand().toString() + " - " + winner.get(i).getBestHand().toStringTier()
								+ " - " + " Player " + winner.get(i).playerNumber + " $" + winner.get(i).getBalance());
			}
		} else {
			winner.get(0).setPlayerBalance(winner.get(0).getBalance() + kitty);
			System.out.println("Winner: Player " + winner.get(0).playerNumber + " $" + winner.get(0).balance);
			this.printSeparator('+', SEPARATOR_LENGTH);
			System.out
					.println(winner.get(0).getBestHand().toStringTier() + " " + winner.get(0).getBestHand().toString());
		}

	}

	// returns the number of players in the game.
	public int getPlayerCount() {
		return players.size();
	}

	// prints the community cards
	public void printCommunityCards() {
		PokerHand cc = new PokerHand(communityCards);
		System.out.println("Community Cards: " + cc.toString());
		this.printSeparator('+', SEPARATOR_LENGTH);
	}

	// outputs each player, cards, balance, etc.
	public void printPlayers() {
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).toString());
			System.out.println("Best Hand: " + players.get(i).getBestHand().toString() + " - "
					+ players.get(i).getBestHand().toStringTier() + "\n");
		}
	}

	// outputs the separator line.
	public void printSeparator(char c, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(c);
		}
		System.out.println();
	}

}
