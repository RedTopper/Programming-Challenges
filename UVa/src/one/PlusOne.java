package one;

import java.util.Scanner;

public class PlusOne {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
	    int input = 1;
	    int intGreaterThanZero = 1;
	    int summationCounter = 1;
	    while(reader.hasNextInt()){     
	    	input = reader.nextInt();
	    	if(input == 1) {System.out.println(1); continue;}
	        intGreaterThanZero = 1; 
	        summationCounter = 1;
	        do {
	            intGreaterThanZero = intGreaterThanZero * 10 + 1;
	            intGreaterThanZero %= input;
	            summationCounter++;
	        } while (intGreaterThanZero != 0);
	        
	        System.out.println(summationCounter);
	    }
	    reader.close();
	}
}
