package carmichael;

import java.util.Scanner;

public class Numbers {
	public static void main(String[] args) {
		int[] numbers = {561, 1105, 1729, 2465, 2821, 6601, 8911, 10585, 15841, 29341, 41041, 46657, 52633, 62745, 63973, 75361};
		Scanner reader = new Scanner(System.in);
		reading: while(reader.hasNextInt()) {
			int n = reader.nextInt();
			if(n == 0) break;
			for(int i : numbers) {
				if(i == n) {
					System.out.println("The number " + n + " is a Carmichael number.");
					continue reading;
				}
			}
			System.out.println(n + " is normal.");
		}
		reader.close();
	}
}
