package permutation;

import java.util.Arrays;
import java.util.Scanner;

public class Simple {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			char[] first = reader.nextLine().toCharArray();
			char[] second = reader.nextLine().toCharArray();
			String output = "";
			for(int i = 0; i < first.length; i++) {
				for(int j = 0; j < second.length; j++) {
					if(first[i] == second[j] && first[i] != 0 && second[j] != 0) {
						output += first[i];
						first[i] = 0;
						second[j] = 0;
					}
				}
			}
			char[] out = output.toCharArray();
			Arrays.sort(out);
			for(char o : out) {
				if(o != 0) System.out.print(o);
			}
			System.out.println();
		}
		reader.close();
	}
}
