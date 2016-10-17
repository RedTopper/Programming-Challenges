package pancake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Flapjack {

	public static boolean isSorted(ArrayList<Integer> sort) {
		for(int i = 0; i < sort.size() - 1; i++) {
			if(sort.get(i + 1) > sort.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> flop(ArrayList<Integer> stack, int pos) {
		ArrayList<Integer> firstPart = new ArrayList<Integer>(stack.subList(0, pos));
		ArrayList<Integer> secondPartFlopped = new ArrayList<Integer>(stack.subList(pos, stack.size()));
		Collections.reverse(secondPartFlopped);
		firstPart.addAll(secondPartFlopped);
		return firstPart;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			System.out.println(line);
			Scanner tolken = new Scanner(line);
			ArrayList<Integer> stack = new ArrayList<>();
			while(tolken.hasNextInt()) {
				stack.add(tolken.nextInt());
			}
			tolken.close();
			int position = 0;
			Collections.reverse(stack);
			while(!isSorted(stack)) {
				int maxIndex = 0;
				int largest = 0;
				for(int i = position; i < stack.size(); i++) {
					if(stack.get(i) > largest) {
						maxIndex = i;
						largest = stack.get(i);
					}
				}
				if(maxIndex != stack.size() - 1) {
					stack = flop(stack, maxIndex);
					System.out.print((maxIndex + 1) + " ");
				}
				stack = flop(stack, position);
				System.out.print((position + 1) + " ");
				position++;
			}
			System.out.println("0");
		}
		scan.close();
	}

}
