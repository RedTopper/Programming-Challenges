package wertyu;

import java.util.Scanner;

public class Wertyu {

	public static final String keyboard = "  qwertyuiop[]\\asdfghjkl;'zxcvbnm,./`1234567890-=".toUpperCase();
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			String  in = reader.nextLine();
			for(int i = 0; i < in.length(); i++) {
				int position = keyboard.substring(1).indexOf(in.substring(i, i+1));
				System.out.print(keyboard.substring(position, position + 1));
			}
			System.out.println();
		}
	}
}
