package primary;

import java.util.Scanner;

public class Arithmetic {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextInt()) {
			int number1 = reader.nextInt();
			int number2 = reader.nextInt();
			if(number1 == 0 && number2 == 0) break;
			int times = 0;
			int carry = 0;
			while (number1 > 0 || number2 > 0) {
				carry = (number1 % 10 + number2 % 10 + carry) / 10;
				number1 = number1 / 10;
				number2 = number2 / 10;
				times += carry;
			}
			System.out.println((times > 0 ? (times > 1 ? times + " carry operations." : times + " carry operation.") : "No carry operation."));
		}
		reader.close();
	}
}
