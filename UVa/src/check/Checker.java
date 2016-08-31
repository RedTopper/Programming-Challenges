package check;

public class Checker {

	public static final int AMOUNT_OF_DOTS = 100;
	public static final char[] PEICES = {'p', 'P', 'n', 'N', 'k', 'K', 'r', 'R', 'q', 'Q', 'b', 'B'};
	public static final char[] FULL;
	static {
		char[] dots = new char[AMOUNT_OF_DOTS + PEICES.length];
		for(int i = 0; i < AMOUNT_OF_DOTS; i++) dots[i] = '.';
		for(int i = AMOUNT_OF_DOTS; i < PEICES.length + AMOUNT_OF_DOTS; i++) dots[i] = PEICES[i-AMOUNT_OF_DOTS];
		FULL = dots;
	}
	
	public static void main(String[] args) {
		for(int board = 0; board < 100; board++) {
			for(int row = 0; row < 8; row++) {
				for(int col = 0; col < 8; col++) {
					System.out.print(FULL[(int)(Math.random() * (int)FULL.length)] + "");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
