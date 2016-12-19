package freckles;

import java.util.Scanner;

public class Freck {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int cases = reader.nextInt();
		while(cases-- > 0) {
			int number = reader.nextInt();
			double[] x = new double[number];
			double[] y = new double[number];
			int[] tree = new int[number];
			for(int i = 0; i < number; i++) {
				x[i] = reader.nextDouble();
				y[i] = reader.nextDouble();
			}
			
			//The simplest Prim algorithm.
			int connect = 1;
		    double weight = 0;
		    tree[0] = 1;
		    while(connect < number) {
		    	double min = -1.0;
		    	int minprim = 0;
		    	for(int i = 0; i < number; i++) {
		    		if(tree[i] == 0) continue; 
	    			for(int j = 0; j < number; j++) { 
	    				if(tree[j] != 0) continue;
	    				double dx = x[i] - x[j]; 
	    				double dy = y[i] - y[j];
	    				double len = Math.sqrt(dx * dx + dy * dy);
	    				if(min == -1.0 || len < min) {
	    					min = len;
	    					minprim = j;
	    				}
	    			}
		    	}
		    	tree[minprim] = 1;
		    	weight += min;
		    	connect++;
		    }
		    System.out.printf("%.2f\n", weight);
		    if(cases > 0) System.out.println();
		}
		reader.close();
	}
}

