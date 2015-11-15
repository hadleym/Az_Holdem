// Author: Mark Hadley
import java.util.ArrayList;
import java.util.Scanner;

public class ArizonaHoldEm {
	// plays multiple games of Arizona Hold '5em with 2-10 players.
	// asks for a number of players [2-10] and then
	// loops while user input is 'y'.
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String answer;
		System.out.print("How many players? ");
		int numberOfPlayers = s.nextInt();
		int numberOfHoleCards = 2;
		int numberOfCommunityCards = 5;
		ArrayList<Player> players = new ArrayList<Player>();
		for (int index = 1; index <= numberOfPlayers; index++) {
			players.add(new Player(index, 100.0));
		}
		
		do {
			Table table = new Table(players, numberOfHoleCards, numberOfCommunityCards);
			table.printCommunityCards();
			players = table.playGame();

			System.out.print("\nPlay another round? y or n: ");
			answer = s.next();
			System.out.println();
		} while (answer.equals("y"));
		s.close();
	}

}
