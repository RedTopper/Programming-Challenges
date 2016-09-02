package jolly;

import java.util.Scanner;

public class Jolly {

	public static void main(String[] args) {
		try {
			Scanner reader = new Scanner(System.in);
			out:
			while(true) {
				int[] jolly = new int[reader.nextInt()];
				boolean[] used = new boolean[jolly.length - 1];
				for(int i = 0; i < jolly.length; i++) {
					jolly[i] = reader.nextInt();
				}
				for(int i = 0; i < jolly.length - 1; i++) {
					int absAbs = Math.abs(jolly[i] - jolly[i+1]) - 1;
					if(absAbs >= 0 && absAbs < used.length) {
						used[absAbs] = true;
					}
				}
				for(boolean b : used) {
					if(!b) {
						System.out.println("Not jolly");
						continue out;
					}
				}
				System.out.println("Jolly");
			}
		} catch (Exception e) {
			
		}
	}
}
