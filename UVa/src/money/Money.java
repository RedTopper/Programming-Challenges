package money;

import java.util.Scanner;

public class Money {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(true) {
			int n = reader.nextInt();
			if(n <= 0 || n > 1000) {
				break;
			}
			double[] money = new double[n];
			double average = 0.0;
			double positiveDifferences = 0.0;
			double negativeDifferences = 0.0;
			for(int i = 0; i < n; i++) {
				money[i] = reader.nextDouble();
				average += money[i];
			}
			average /= (double)n;
			for(int i = 0; i < n; i++) {
				double difference = (double)((long)((money[i] - average) * 100.0))/100.0;
				if(difference < 0) {
					negativeDifferences += difference;
				} else {
					positiveDifferences += difference;
				}
			}
			if(negativeDifferences * -1.0 > positiveDifferences) {
				System.out.printf("$%.2f\n", negativeDifferences * -1.0);
			} else {
				System.out.printf("$%.2f\n", positiveDifferences);
			}
		}
		reader.close();
	}
}
