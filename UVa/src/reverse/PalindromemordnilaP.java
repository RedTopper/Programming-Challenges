package reverse;

import java.util.Scanner;

public class PalindromemordnilaP {
	public static boolean isPalindrome(String s) {
		for(int i = 0; i < s.length() / 2; i++) {
			if(!s.substring(i,i + 1).equals(s.substring(s.length() - 1 - i, s.length() - i))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int numbers = reader.nextInt();reader.nextLine();
		while (numbers-- > 0) {
			String number = reader.nextLine();
			int iterations  = 0;
			do {
				iterations++;
				number = Long.toString(Long.parseLong(number) + Long.parseLong(new StringBuilder(number).reverse().toString()));
			} while(!isPalindrome(number));
			System.out.println(iterations + " " + number);
		}
		reader.close();
	}
}
