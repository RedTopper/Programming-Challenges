package light;

import java.util.Scanner;

public class Light {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextLong()) {
			long n = reader.nextLong();
			if(n == 0l) break;
			long root = (long) Math.sqrt(n);
			if(root * root == n) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
		reader.close();
	}
}
