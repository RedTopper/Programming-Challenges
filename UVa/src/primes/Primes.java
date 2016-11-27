package primes;

import java.util.Scanner;

public class Primes {
	static class Metadata {
		int[] primes;
		boolean[] isComposite;
	}

	public static void main(String[] args) {
		Metadata meta = generatePrimes(10000001);
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextInt()) {
			int n = reader.nextInt();
			if (n < 8){
				System.out.println("Impossible.");
				continue;
			} else if (n % 2 == 0) {
				System.out.print("2 2 ");
			} else {
				System.out.print("2 3 ");
			}
			
			n = n - (2 + 2 + n % 2);
			for (int i = 0; i < meta.primes.length; i++) {
				if (!meta.isComposite[n - meta.primes[i]]) {
					System.out.println(meta.primes[i] + " " + (n - meta.primes[i]));
					break;
				}
			}
		}
		reader.close();
	}
	
	//Generation of primes found at http://stackoverflow.com/q/586284
	private static Metadata generatePrimes(int max) {
	    boolean[] isComposite = new boolean[max + 1];
	    for (int i = 2; i * i <= max; i++) {
	        if (!isComposite [i]) {
	            for (int j = i; i * j <= max; j++) {
	                isComposite [i*j] = true;
	            }
	        }
	    }
	    int numPrimes = 0;
	    for (int i = 2; i <= max; i++) {
	        if (!isComposite [i]) numPrimes++;
	    }
	    int [] primes = new int [numPrimes];
	    int index = 0;
	    for (int i = 2; i <= max; i++) {
	        if (!isComposite [i]) primes [index++] = i;
	    }
	    Metadata meta = new Metadata();
	    meta.primes = primes;
	    meta.isComposite = isComposite;
	    return meta;
	}
}
