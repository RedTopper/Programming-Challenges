package minesweeper;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int fieldNumber = 0;
		while(true) {
			fieldNumber++;
			int n = reader.nextInt();
			int m = reader.nextInt();
			if(n <= 0 || m > 100) {
				break;
			} else if(fieldNumber > 1) {
				System.out.println();
			}
			char[][] field = new char[n+2][m+2];
			for(int row = 0; row < n; row++) {
				char[] input = reader.next().replaceAll("[^\\*\\.]", "").toCharArray();
				if(input.length < m) {
					break;
				}
				for(int col = 0; col < m; col++) {
					if(input[col] == '*') {
						field[row+1][col+1] = '*';
						for(int rowOffset = 0; rowOffset < 3; rowOffset++) {
							for(int colOffset = 0; colOffset < 3; colOffset++) {
								if(field[row+rowOffset][col+colOffset] != '*')
								field[row+rowOffset][col+colOffset] += (char)1;
							}
						}
					}
				}
			}
			System.out.println("Field #" + fieldNumber + ":");
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < m; col++) {
					System.out.print((char)(field[row + 1][col + 1] + (field[row + 1][col + 1] == '*' ? '\0' : '0')));
				}
				System.out.println();
			}
		}
		reader.close();
	}
}