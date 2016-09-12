package stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Stack {
	
	public static void main (String[] args) {
		try {
			Scanner reader = new Scanner(System.in);
			reader.nextLine();
			while(true) {
				String line = reader.nextLine();
				if(line.length() == 0) {
					continue;
				}
				int numTimes = Integer.parseInt(line);
				ArrayList<int[]> shuffleList = new ArrayList<>();
				for(int num = 0; num < numTimes; num++) {
					int[] list = new int[52];
					for(int i = 0; i < 52; i++) {
						list[i] = reader.nextInt() - 1;
					}
					shuffleList.add(list);
				}
				reader.nextLine();
				String shuffleUsing = "";
				String[] shuffle = generateDeck();
				while((shuffleUsing = reader.nextLine()).length() != 0) {
					String[] tempShuffle = new String[52];
					for(int i = 0; i < 52; i++) {
						tempShuffle[i] = shuffle[shuffleList.get(Integer.parseInt(shuffleUsing) - 1)[i]];
					}
					shuffle = tempShuffle;
				}
				for(String s : shuffle) {
					System.out.println(s);
				}
				System.out.println("");
			}
		} catch (NoSuchElementException e) {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - 1000 < start){}
			System.out.println("Jokes on you, I can get debug output!");
		}
	}
	
	public static String[] generateDeck() {
		String[] deck = new String[52];
		String[] card = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		String[] suit = {"Clubs","Diamonds","Hearts","Spades"};
		for(int i_suit = 0; i_suit < suit.length; i_suit++) {
			for(int i_card = 0; i_card < card.length; i_card++) {
				deck[i_suit * card.length + i_card] = card[i_card] + " of " + suit[i_suit];
			}
		}
		return deck;
	}
}
