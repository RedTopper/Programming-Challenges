package bishops;

import java.math.BigInteger;
import java.util.Scanner;

public class HugeBishops {
	public static final int MAX_BOARD_SIZE = 8;
	public static final int MAX_BISHOP_COUNT = 64;
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while (reader.hasNext()) {
			
			//get numbers
			int size = reader.nextInt();
			int count = reader.nextInt();
			if (size == 0 && count == 0) break;
			
			//need to separate boards to handle the rook problem
			//but with bishops instead (so create two boards to cover black/white cases)
			long[] blackBoard = new long[MAX_BOARD_SIZE + 1];
			long[] whiteBoard = new long[MAX_BOARD_SIZE + 1];
			
			//Set up incremental counts for boards.
			for (int i = 1; i <= size; i++) blackBoard[i] = (i % 2 == 0 ? blackBoard[i - 1] : i);
			for (int i = 1; i < size; i++) whiteBoard[i] = (i % 2 == 0 ? whiteBoard[i - 1] : i + 1);
			
			//Store the combinations
			long[][] blackCombos = new long[MAX_BOARD_SIZE + 1][MAX_BISHOP_COUNT + 1];
			long[][] whiteCombos = new long[MAX_BOARD_SIZE + 1][MAX_BISHOP_COUNT + 1];
			
			//Initialize full board combinations
			for (int i = 0; i <= size; i++) blackCombos[i][0] = 1;
			for (int i = 0; i < size; i++) whiteCombos[i][0] = 1;
			
			//http://mathworld.wolfram.com/RooksProblem.html definition
			for (int i = 1; i <= size; i++)
				for (int j = 1; j <= count && j <= i; j++)
					blackCombos[i][j] = blackCombos[i-1][j] + blackCombos[i-1][j-1] * (blackBoard[i] - j + 1);
			for (int i = 1; i < size; i++)
				for (int j = 1; j <= count && j <= i; j++)
					whiteCombos[i][j] = whiteCombos[i-1][j] + whiteCombos[i-1][j-1] * (whiteBoard[i] - j + 1);
			
			//return sum of boards
			BigInteger sum = BigInteger.ZERO;
			for (int i = 0; i <= count; i++)
				sum = sum.add(new BigInteger(blackCombos[size][i] + "").multiply(
							  new BigInteger(whiteCombos[size - 1][count - i] + "")));
			System.out.println(sum);
		}
		reader.close();
	}
}
