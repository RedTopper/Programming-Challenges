package euclid;

import java.util.Scanner;

public class Euclid {
	static class XY {
		public int x = 0, y = 0;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextInt()) {
			int a = reader.nextInt();
			int b = reader.nextInt();
			XY xy = new XY();
			
			int d = getD(a, b, xy);
			if(a == b) {
				xy.x = 0;
				xy.y = 1;
			}
			System.out.println(xy.x + " " + xy.y + " " + d);
		}
		reader.close();
	}

	private static int getD(int a, int b, XY xy0) {
	    if (a == 0) {
	    	xy0.x = 0;
	    	xy0.y = 1;
	        return b;
	    }  else {
	        XY xy1 = new XY();
	        int d = getD(b%a, a, xy1);
	        xy0.x = xy1.y - (b / a) * xy1.x;
	        xy0.y = xy1.x;
	        return d;
	    }
	}
}