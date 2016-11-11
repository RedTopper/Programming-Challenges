package count;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Count {
	public static void main(String[] args) {
		BigInteger points[] = new BigInteger[1001];
		Arrays.fill(points, BigInteger.ZERO);
		points[0] = BigInteger.valueOf(1); //ways to write 0
		points[1] = BigInteger.valueOf(2); //ways to write 1
		points[2] = BigInteger.valueOf(5); //ways to write 2 (11, 14, 41, 44, 2)
		points[3] = BigInteger.valueOf(13);//ways to write 3
		
	    for(int i = 4; i <= 1000; i++) {
	        points[i] = points[i].add(points[i - 1].multiply(BigInteger.valueOf(2))); //two ways to write 1
	        points[i] = points[i].add(points[i - 2]);
	        points[i] = points[i].add(points[i - 3]);
	    }
	    
	    Scanner reader = new Scanner(System.in);
	    while(reader.hasNextInt()) {
	    	System.out.println(points[reader.nextInt()]);
	    }
 	}
}
