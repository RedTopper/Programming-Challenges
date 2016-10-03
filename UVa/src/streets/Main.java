import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int numberOfLines = reader.nextInt();
		for(int i = 0; i < numberOfLines; i++) {
			int size = reader.nextInt();
			int[] address = new int[size];
			for(int j = 0; j < size; j++) {
				address[j] = reader.nextInt();
			}
			Arrays.sort(address);
			int sum = 0;
			int key = address[size/2];
			for(int j = 0; j < size; j++) {
				sum += Math.abs(key - address[j]); 
			}
			System.out.println(sum);
		}
	}
}