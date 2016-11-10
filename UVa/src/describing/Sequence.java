package describing;

import java.util.Scanner;

public class Sequence {	

	public static void main(String[] args) {
		int[] index = new int[673468];
		index[0] = 1;
		index[1] = 2;
		index[2] = 4;
		out: for(int i = 1; true; i++) {
			for(int j = index[i]; j < index[i + 1]; j++) {
				if(index[index[i] - 1] > 2000000000) break out;
				index[j] = index[j - 1] + i + 1;
			}
		}
		Scanner reader = new Scanner(System.in);
		while(true) {
			int num = reader.nextInt();
			if(num == 0) break;
			System.out.println(search(num, index) + 1);
		}
		reader.close();
	}
	
	//http://stackoverflow.com/a/30245398
	public static int search(int value, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        int lastValue = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            lastValue = a[mid];
            if (value < lastValue) {
                hi = mid - 1;
            } else if (value > lastValue) {
                lo = mid + 1;
            } else {
                return mid; //if found return index
            }
        }
        return hi; //otherwise return the next highest index
    }
}
