package check;

import java.util.Scanner;

public class Uncheck {
	public static final int WIDTH = 8,HEIGHT = 8;
	
	public static enum Bound {
		BAD_KING,
		SOMETHING,
		NOTHING,
		OUT_OF_BOUNDS
	}

	private static enum Direction {
		U(0,1),
		UR(1,1),
		R(1,0),
		RD(1,-1),
		D(0,-1),
		DL(-1,-1),
		L(-1,0),
		LU(-1,1);
		
		private int row,col;
		private Direction(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public int getRow() {
			return row;
		}
		public int getCol() {
			return col;
		}
	}
	
	public static final Direction streight[] = {Direction.D, Direction.L, Direction.U, Direction.R};
	public static final Direction diagnal[] = {Direction.UR, Direction.RD, Direction.DL, Direction.LU};
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		char[][] board = new char[HEIGHT][WIDTH];
		int gameNumber = 0;
		out:
		while (true) {
			boolean empty = true;
			gameNumber++;
			board = new char[HEIGHT][WIDTH];
			for(int row = 0; row < HEIGHT; row++) {
				char[] line = reader.nextLine().toCharArray();
				if(line.length == 8) {
					for(int col = 0; col < WIDTH; col++) {
						board[row][col] = line[col];
						if(line[col] != '.') empty = false;
					}
				} else {
					row--;
				}
			}
			if(empty) break out;
			Bound bound = Bound.NOTHING;
			in:
			for(int row = 0; row < HEIGHT; row++) {
				for(int col = 0; col < WIDTH; col++) {
					if(board[row][col] == 'K' || board[row][col] == 'k') {
						for(int direction = 0; direction < Direction.values().length; direction++) {
							for(int distance = 1; distance < 8; distance++) {
								bound = isCheckerAt(board, row, col, row + Direction.values()[direction].getRow() * distance, col + Direction.values()[direction].getCol() * distance, Direction.values()[direction], gameNumber, distance);
								if(bound == Bound.SOMETHING ||
								   bound == Bound.OUT_OF_BOUNDS){
									break;
								}
								if(bound == Bound.BAD_KING){
									break in;
								}
							}
						}
						for(int direction = 0; direction < 4; direction++) {
							for(int adder = 0; adder < 2; adder++) {
								int position = direction + adder;
								if(position >= 4) {
									position = 0;
								}
								bound = isCheckerAt(board, row, col, row + diagnal[direction].getRow() * 2 + streight[position].getRow(), col + diagnal[direction].getCol() * 2 + streight[position].getCol(), null, gameNumber, 0);
								if(bound == Bound.BAD_KING){
									break in;
								}
							}
						}
					}
				}
			}
			if(bound != Bound.BAD_KING) {
				System.out.println("Game #" + gameNumber + ": no king is in check.");
			}
		}
		reader.close();
	}

	private static Bound isCheckerAt(char[][] board, int row, int col, int checkRow, int checkCol, Direction direction, int gameNumber, int distance) {
		if(checkRow < 0 || checkRow >= HEIGHT || checkCol < 0 || checkCol >= WIDTH) return Bound.OUT_OF_BOUNDS;
		boolean currentWhite = (board[row][col] == 'K' ? true : false);
		boolean checkWhite = (board[checkRow][checkCol] >= 'A' && board[checkRow][checkCol] <= 'Z'  ? true : false);
		boolean check = false;
		if(direction != null) {
			switch (board[checkRow][checkCol]) {
			case '.':
				return Bound.NOTHING;
			case 'r':
			case 'R':
				if(contains(diagnal, direction)) return Bound.SOMETHING;
				check = currentWhite != checkWhite;
				break;
			case 'b':
			case 'B':
				if(contains(streight, direction)) return Bound.SOMETHING;
				check = currentWhite != checkWhite;
				break;
			case 'q':
			case 'Q':
				check = currentWhite != checkWhite;
				break;
			case 'p':
			case 'P':
				if(contains(streight, direction)) return Bound.SOMETHING;
				if(distance == 1 && currentWhite != checkWhite) {
					if(board[checkRow][checkCol] == 'p' && checkRow < row) {
						check = true;
					} 
					if (board[checkRow][checkCol] == 'P' && checkRow > row ) {
						check = true;
					}
				}
				break;
			}
		} else {
			switch (board[checkRow][checkCol]) {
			case '.':
				return Bound.NOTHING;
			case 'n':
			case 'N':
				check = currentWhite != checkWhite;
				break;
			}
		}
		if(check) {
			System.out.println("Game #" + gameNumber + ": " + (currentWhite ? "white" : "black") + " king is in check.");
			return Bound.BAD_KING;
		} else {
			return Bound.SOMETHING;
		}
	}
	
	private static boolean contains(Direction[] directions, Direction direction) {
		for(Direction d : directions) {
			if(direction.equals(d)) return true;
		}
		return false;
	}
}
