package tug;

import java.util.Scanner;

public class War {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int cases = reader.nextInt();
		
	    while (cases--  > 0) {
	        int people = reader.nextInt();
	        int total = 0;
	        int[] weights = new int[people];
	        
	        for (int i = 0; i < people; i++) { 
	        	weights[i] = reader.nextInt();
	        	total += weights[i]; 
	        }
	        
	        long[] line = new long[total / 2 + 1];
	        line[0] = 1;
	        
	        for (int i = 0; i < people; i++) {
	        	int weight = weights[i];
	        	for (int j = total/2; j >= weight; j--) {
	        		line[j] |= line[j - weight] << 1l;
	        	}
	        }
	        
	        boolean balanced = true;
	        int i = 0;
	        for(i = total / 2; balanced; i--) {
	            long val = line[i];
	            balanced = false;
	            if ((val & (1l << people / 2)) == 0) balanced = true;
	            if (balanced && ((people % 2) == 1) && (val & (1l << (people / 2 + 1))) != 0) balanced = false; //wtf?
	        } i++; //add one to diff due to for loop subtraction
	        
		    System.out.println(i + " " + (total - i));
		    if(cases != 0)System.out.println();
	    }
	    reader.close();
	}
}
