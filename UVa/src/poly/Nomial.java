package poly;

import java.util.Scanner;

public class Nomial {
	public static long factorial(long n) {
		return ((n > 1) ? n * factorial(n-1) : 1);
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextInt()) {
			long output = 1;
			int power = reader.nextInt();
			int numberOfVars = reader.nextInt();
			for(int i = 0; i < numberOfVars; i++) {
				output *= factorial(reader.nextInt());
			}
			output = factorial(power)/output;
			System.out.println(output);
		}
		reader.close();
	}
}
