package yahtzee;

import java.util.Scanner;

public class Yahtzee {
	public static void main(String args[]) {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while(true) {
			int[][] game = new int[13][5];
			for(int roll = 0; roll < 13; roll++) {
				for(int die = 0; die < 5; die++) {
					game[roll][die] = reader.nextInt();
				}
			}
			
			//calc total of simple sum {
			int[][] score = new int[13][6];
			for(int roll = 0; roll < 13; roll++) {
				for(int of = 1; of <= 6; of++) {
					score[roll][of-1] = scoreSum(game[roll], of);
				}
			}
		}
	}
	
	public static int scoreSum(int[] die, int of) {
		int total = 0;
		for(int i = 0; i < die.length; i++) {
			if(die[i] == of) {
				total += of;
			}
		}
		return total;
	}
	
	public int scoreKind(int[] die, int amount) {
		int total = 0;
		int bestNumberMatching = 0;
		for(int faceValue = 0; faceValue < 6; faceValue++) {
			int currentNumberMatching = 0;
			for(int i = 0; i < die.length; i++) {
				if(die[i] == faceValue) {
					currentNumberMatching++;
				}
			}
			if(currentNumberMatching > bestNumberMatching) {
				bestNumberMatching = currentNumberMatching;
			}
		}
		for(int i = 0; i < die.length; i++) {
			total += die[i];
		}
		if(bestNumberMatching >= amount) {
			if(amount == 5) {
				return 50;
			} else {
				return total;
			}
		} else {
			return 0;
		}
	}
	
	public int scoreStraight(int[] die, boolean isShort) {
		int casesToTry = (isShort ? 3 : 2);
		int amountRequired = (isShort ? 4 : 5);
		for(int cases = 1; cases <= casesToTry; cases++) {
			int amountCurrently = 0;
			for(int i = 0; i < amountRequired; i++) {
				if(contains(die, cases+ i)) {
					amountCurrently++;
				}
			}
			if(amountCurrently >= amountRequired) {
				return (isShort ? 25 : 35);
			}
		}
		return 0;
	}
	
	public int scoreFullHouse(int[] die) {
		boolean foundTwo = false;
		boolean foundThree = false;
		for(int faceValue = 0; faceValue < 6; faceValue++) {
			int currentNumbersMatching = 0;
			for(int i = 0; i < die.length; i++) {
				if(die[i] == faceValue) {
					currentNumbersMatching++;
				}
			}
			if(!foundTwo && currentNumbersMatching == 2) {
				foundTwo = true;
			}
			if(!foundThree && currentNumbersMatching == 3) {
				foundThree = true;
			}
		}
		if(foundTwo && foundThree) {
			return 40;
		} else {
			return 0;
		}
	}
	
	public int scoreChance(int[] die) {
		int total = 0;
		for(int face : die) {
			total += face;
		}
		return total;
	}
	
	public boolean contains(int[] die, int num) {
		for(int i = 0; i < die.length; i++) {
			if(die[i] == num) {
				return true;
			}
		}
		return false;
	}
}
