package fibinachi;

import java.math.BigInteger;
import java.util.Scanner;

public class Fibcalc {	

	public static void main(String[] args) {
		BigInteger[] fibs = new BigInteger[500];
		fibs[0] = BigInteger.ONE;
		fibs[1] = BigInteger.ONE;
		for(int i = 2; i < fibs.length; i++) {
			fibs[i] = fibs[i - 2].add(fibs[i - 1]);
		}
		Scanner reader = new Scanner(System.in);
		while(true) {
			BigInteger first = reader.nextBigInteger();
			BigInteger second = reader.nextBigInteger();
			if(first.compareTo(second) > 0) {
				BigInteger temp = second;
				first = second;
				second = temp;
			}
			if(first.equals(BigInteger.ZERO) && second.equals(BigInteger.ZERO)) {
				break;
			}
			int count = 0;
			for(int i = 1; i < fibs.length; i++) {
				if(fibs[i].compareTo(first) >= 0) {
					if(fibs[i].compareTo(second) <= 0) {
						count++;
					} else {
						break;
					}
				}
			}
			System.out.println(count);
		}
		reader.close();
	}
}
