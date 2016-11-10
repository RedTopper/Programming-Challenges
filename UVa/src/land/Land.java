package land;

import java.math.BigInteger;
import java.util.Scanner;

public class Land {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int amount = reader.nextInt();
		while(amount-- > 0) {
			int n = reader.nextInt();
			//|    ln     |   |           rn           |
			//(N-0)*(N-1)/2 + (N-0)*(N-1)*(N-2)*(N-3)/24 + 1
			int lnm0, lnm1, rnm0, rnm1, rnm2, rnm3; 
			if((n - 0) % 2 == 0) { // n - 0 is even
				lnm0 = (n - 0) / 2;
				lnm1 = (n - 1);
				if((n - 0) % 4 == 0) { //n - 0 is divisible by 4
					rnm0 = (n - 0) / 4;
					rnm1 = (n - 1);
					rnm2 = (n - 2) / 2;
					rnm3 = (n - 3);
				} else {
					rnm0 = (n - 0) / 2;
					rnm1 = (n - 1);
					rnm2 = (n - 2) / 4;
					rnm3 = (n - 3);
				}
			} else { //n - 1 is even
				lnm0 = (n - 0);
				lnm1 = (n - 1) / 2;
				if((n - 1) % 4 == 0) { //n - 1 is divisible by  4 
					rnm0 = (n - 0);
					rnm1 = (n - 1) / 4;
					rnm2 = (n - 2);
					rnm3 = (n - 3) / 2;
				} else {
					rnm0 = (n - 0);
					rnm1 = (n - 1) / 2;
					rnm2 = (n - 2);
					rnm3 = (n - 3) / 4;
				}
			}
			
			//finish dividing out that 3 on the right hand side. (2 * 4 * 3 = 24)!
			if(rnm0 % 3 == 0) {
				rnm0 /= 3;
			} else if(rnm1 % 3 == 0) {
				rnm1 /= 3;
			} else if(rnm2 % 3 == 0) {
				rnm2 /= 3;
			} else {
				rnm3 /= 3;
			}
			
			//Now we have no division to do! If this was implemented in C, this would be so fast!
			System.out.println((BigInteger.valueOf(lnm0).multiply(BigInteger.valueOf(lnm1))).add(
							   (BigInteger.valueOf(rnm0).multiply(BigInteger.valueOf(rnm1)).multiply(BigInteger.valueOf(rnm2)).multiply(BigInteger.valueOf(rnm3))).add(
							    BigInteger.ONE)));
		}
		reader.close();
	}
}
