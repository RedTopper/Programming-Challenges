package quehuehue;

import java.util.Scanner;

public class HeightQueue {
	public static final int MAX_QUEUE_LENGTH = 13;

	public static void main(String[] args) {
	    int[][][] queue = new int[MAX_QUEUE_LENGTH + 2][MAX_QUEUE_LENGTH + 2][MAX_QUEUE_LENGTH + 2];
	   
	    //compute 3D array of all possible queue lengths
	    queue[1][1][1] = 1;
	    for (int numPeople = 2; numPeople < MAX_QUEUE_LENGTH + 2; numPeople++)
	        for (int seenFromBeginning = 1; seenFromBeginning <= numPeople; seenFromBeginning++)
	            for (int seenFromEnd = 1; seenFromEnd <= numPeople; seenFromEnd++)
	            	queue[numPeople    ][seenFromBeginning    ][seenFromEnd    ] = 
	            	queue[numPeople - 1][seenFromBeginning    ][seenFromEnd - 1] +				 
	            	queue[numPeople - 1][seenFromBeginning - 1][seenFromEnd    ] + 
	            	queue[numPeople - 1][seenFromBeginning    ][seenFromEnd    ] * (numPeople - 2);
	    
	    //read user input
	    Scanner reader = new Scanner(System.in);
	    int cases = reader.nextInt();
	    while(cases-- > 0) System.out.println(queue[reader.nextInt()][reader.nextInt()][reader.nextInt()]);
	    reader.close();
	}
}
