package check;

import java.util.Scanner;

public class Check {
	public static final int WIDTH = 8, HEIGHT = 8;
	
	public static enum Color {
		WHITE('K'),
		BLACK('k');
			
		private char side;
		private Color(char side) {this.side = side;}
		public char getKing() {return side;}
		public char getBadKing() {return (side == 'k' ? 'K' : 'k');}
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
	
	public static enum Bound {
		BAD_KING,
		SOMETHING,
		NOTHING,
		OUT_OF_BOUNDS
	}
	
	public static final Direction streight[] = {Direction.U, Direction.R, Direction.D, Direction.L};
	public static final Direction diagnal[] = {Direction.UR, Direction.RD, Direction.DL, Direction.LU};
	
	private static class Board {
		private char[][] board = new char[WIDTH][HEIGHT];
		private boolean isEmpty = true;
		
		public boolean isEmpty() {
			return isEmpty;
		}
		
		public void writeBoard(int row, int col, char c) {
			if(c != '.') {
				isEmpty = false;
			}
			board[row][col] = c;
		}
		
		public Bound isBadKingAt(int row, int col, Color peiceColor) {
			if(row < 0 || row >= WIDTH) {
				return Bound.OUT_OF_BOUNDS;
			}
			if(col < 0 || col >= HEIGHT) {
				return Bound.OUT_OF_BOUNDS;
			}
			if(board[row][col] == peiceColor.getBadKing()) {
				return Bound.BAD_KING;
			} 
			if(board[row][col] == '.') {
				return Bound.NOTHING;
			} 
			return Bound.SOMETHING;
		}
		
		public Color getColorAt(int row, int col) {
			if(board[row][col] >= 'a' && board[row][col] <= 'z') {
				return Color.BLACK;
			} else {
				return Color.WHITE;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while (true) {
			Board board = new Board();
			for(int row = 0; row < HEIGHT; row++) {
				char[] line = reader.nextLine().toCharArray();
				for(int col = 0; col < WIDTH; col++) {
					board.writeBoard(row, col, line[col]);
				}
			}
			if(board.isEmpty()) break;
			boolean check = false;
			out:
			for(int row = 0; row < HEIGHT; row++) {
				for(int col = 0; col < WIDTH; col++) {
					switch(board.board[row][col]) {
					case 'p':
						if(board.isBadKingAt(row + 1, col - 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						if(board.isBadKingAt(row + 1, col + 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						break;
					case 'P':
						if(board.isBadKingAt(row - 1, col - 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						if(board.isBadKingAt(row - 1, col + 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						break;
					case 'q':
					case 'Q':
					case 'b':
					case 'B':
						for(int direction = 0; direction < 4; direction++) {
							for(int distance = 1; distance < HEIGHT; distance++) {
								Bound bound = board.isBadKingAt(row + distance * diagnal[direction].getRow(), col + distance * diagnal[direction].getCol(), board.getColorAt(row, col));
								if(bound == Bound.SOMETHING ||
								   bound == Bound.OUT_OF_BOUNDS ||
								   bound == Bound.BAD_KING){
									break;
								} else {
									check = true;
									break out;
								}
							}
						}
						if(board.board[row][col] != 'q' || board.board[row][col] != 'Q') break;
					case 'r':
					case 'R':
						for(int direction = 0; direction < 4; direction++) {
							for(int distance = 1; distance < HEIGHT; distance++) {
								Bound bound = board.isBadKingAt(row + distance * streight[direction].getRow(), col + distance * streight[direction].getCol(), board.getColorAt(row, col));
								if(bound == Bound.SOMETHING ||
								   bound == Bound.OUT_OF_BOUNDS ||
								   bound == Bound.BAD_KING){
									break;
								} else {
									check = true;
									break out;
								}
							}
						}
						break;
					}
				}
			}
			if(check) {
				System.out.println("Kill yourself");
			} else {
				System.out.println("Don't do it!");
			}
		}
		reader.close();
	}
}
