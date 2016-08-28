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
	
	public static final Direction streight[] = {Direction.D, Direction.L, Direction.U, Direction.R};
	public static final Direction diagnal[] = {Direction.UR, Direction.RD, Direction.DL, Direction.LU};
	
	private static class Board {
		private char[][] board = new char[WIDTH][HEIGHT];
		private boolean isEmpty = true;
		private Board debugBoard;
		private boolean debugMode = false;
		
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
			if(debugMode) {
				debugBoard.board[row][col] = '*';
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

		/*
		public void startDebugging() {
			debugBoard = new Board();
			debugMode = true;
		}

		public void stopDebugging() {
			if(debugMode) {
				for(char[] row : debugBoard.board) {
					for(char col : row) {
						System.out.print(col);
					}
					System.out.println();
				}
				debugMode = false;
			}
		}
		*/
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int gameNumber = 0;
		while (true) {
			gameNumber++;
			Board board = new Board();
			for(int row = 0; row < HEIGHT; row++) {
				char[] line = reader.nextLine().toCharArray();
				if(line.length == 8) {
					for(int col = 0; col < WIDTH; col++) {
						board.writeBoard(row, col, line[col]);
					}
				} else {
					row--;
				}
			}
			if(board.isEmpty()) break;
			boolean check = false;
			int row = 0, col = 0;
			out:
			for(row = 0; row < HEIGHT; row++) {
				for(col = 0; col < WIDTH; col++) {
					switch(board.board[row][col]) {
					case 'p':
						//board.startDebugging();
						if(board.isBadKingAt(row + 1, col - 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						if(board.isBadKingAt(row + 1, col + 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						break;
					case 'P':
						//board.startDebugging();
						if(board.isBadKingAt(row - 1, col - 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						if(board.isBadKingAt(row - 1, col + 1, board.getColorAt(row, col)) == Bound.BAD_KING) {check = true; break out;}
						break;
					case 'q':
					case 'Q':
					case 'b':
					case 'B':
						//board.startDebugging();
						for(int direction = 0; direction < 4; direction++) {
							for(int distance = 1; distance < HEIGHT; distance++) {
								Bound bound = board.isBadKingAt(row + distance * diagnal[direction].getRow(), col + distance * diagnal[direction].getCol(), board.getColorAt(row, col));
								if(bound == Bound.SOMETHING ||
								   bound == Bound.OUT_OF_BOUNDS){
									break;
								} 
								if(bound == Bound.BAD_KING){
									check = true;
									break out;
								}
							}
						}
						if(board.board[row][col] != 'q' || board.board[row][col] != 'Q') break;
					case 'r':
					case 'R':
						//board.startDebugging();
						for(int direction = 0; direction < 4; direction++) {
							for(int distance = 1; distance < HEIGHT; distance++) {
								Bound bound = board.isBadKingAt(row + distance * streight[direction].getRow(), col + distance * streight[direction].getCol(), board.getColorAt(row, col));
								if(bound == Bound.SOMETHING ||
								   bound == Bound.OUT_OF_BOUNDS){
									break;
								}
								if(bound == Bound.BAD_KING){
									check = true;
									break out;
								}
							}
						}
						break;
					case 'k':
					case 'K':
						//board.startDebugging();
						for(int direction = 0; direction < 8; direction++) {
							Bound bound = board.isBadKingAt(row + Direction.values()[direction].getRow(), col + Direction.values()[direction].getCol(), board.getColorAt(row, col));
							if(bound == Bound.BAD_KING){
								check = true;
								break out;
							}
						}
						break;
					case 'n':
					case 'N':
						//board.startDebugging();
						for(int direction = 0; direction < 4; direction++) {
							for(int adder = 0; adder < 2; adder++) {
								int position = direction + adder;
								if(position >= 4) {
									position = 0;
								}
								Bound bound = board.isBadKingAt(row + diagnal[direction].getRow() * 2 + streight[position].getRow(), col + diagnal[direction].getCol() * 2 + streight[position].getCol(), board.getColorAt(row, col));
								if(bound == Bound.BAD_KING){
									check = true;
									break out;
								}
							}
						}
						break;
					}
					//board.stopDebugging();
				}
			}
			System.out.print("Game #" + gameNumber + ": ");
			if(check) {
				//board.stopDebugging();
				//System.out.println("The thing at (" + row + "," + col + ") is putting the king in check!");
				System.out.println((board.getColorAt(row, col) == Color.WHITE ? "black" : "white") + " king is in check.");
			} else {
				//System.out.println("Nothing to see here.");
				System.out.println("no king is in check.");
			}
		}
		reader.close();
	}
}