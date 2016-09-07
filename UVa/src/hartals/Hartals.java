package hartals;

import java.util.Scanner;

public class Hartals {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int number = reader.nextInt();
		for(int entries = 0; entries < number; entries++) {
			int days = reader.nextInt();
			if(days < 7 || days > 3650) {break;}
			int parties = reader.nextInt();
			int[] param = new int[parties];
			for(int i = 0; i < parties; i++) {
				param[i] = reader.nextInt();
			}
			int numberOfDays = 0;
			counting:
			for(int day = 1; day <= days; day++) {
				if(day % 7 == 0 || (day + 1) % 7 == 0) {
					continue;
				}
				for(int  i = 0; i < param.length; i++) {
					if(day % param[i] == 0) {
						numberOfDays++;
						continue counting;
					}
				}
			}
			System.out.println(numberOfDays);
		}
	}
}
