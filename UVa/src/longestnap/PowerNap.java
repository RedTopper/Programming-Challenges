package longestnap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PowerNap {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int day = 1;
		while(reader.hasNextLine()) {
			int appointments = Integer.parseInt(reader.nextLine());
			int[][] times = new int[appointments + 2][2];
			times[0][1] = 10 * 60;
			times[appointments + 1][0] = 18 * 60;
			int linenumber = 0;
			while(linenumber < appointments && reader.hasNextLine()) {
				Scanner appointment = new Scanner(reader.nextLine().replaceAll("[\\D]", " "));
				for(int i = 0; i < 2; i++) {
					times[linenumber + 1][i] = appointment.nextInt() * 60 + appointment.nextInt();
				}
				appointment.close();
				linenumber++;
			}
			Arrays.sort(times, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[0] - b[0];
				}
			});
			int maxNap = 0;
			int maxIndex = 0;
			for(int i = 0; i < appointments + 1; i++) {
				if(maxNap < times[i + 1][0] - times[i][1]) {
					maxNap = times[i + 1][0] - times[i][1];
					maxIndex = i;
				}
			}
			System.out.println("Day #" + day + ": the longest nap starts at " + String.format("%02d", (times[maxIndex][1]/60)) + ":" + String.format("%02d", (times[maxIndex][1] % 60)) + " and will last for " + ((maxNap / 60) > 0 ? (maxNap / 60) + " hours and " : "") + (maxNap % 60) + " minutes.");
			day++;
		}
		reader.close();
	}
}
