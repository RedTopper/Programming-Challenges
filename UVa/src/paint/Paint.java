package paint;

import java.util.Scanner;

public class Paint {
	
	public static void fill(char[][] canvas, int row, int col, char replacingColor, char userColor) {
		if(row >= 0 && row < canvas.length && col >= 0 && col < canvas[0].length) {
			if(canvas[row][col] == replacingColor && canvas[row][col] != userColor) {
				canvas[row][col] = userColor;
				for(int i = -1; i <= 1; i+=2) {
					fill(canvas, row + i, col, replacingColor, userColor);
					fill(canvas, row, col + i, replacingColor, userColor);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		char[][] canvas = new char[1][1];
		out:
		while(true) {
			String user = reader.next();
			switch(user) {
			case "I":
				int i_col = reader.nextInt();
				int i_row = reader.nextInt();
				canvas = new char[i_row][i_col];
			case "C":
				for(int row = 0; row < canvas.length; row++) {
					for(int col = 0; col < canvas[0].length; col++) {
						canvas[row][col] = 'O';
					}
				}
				break;
			case "L":
				int l_col = reader.nextInt() - 1;
				int l_row = reader.nextInt() - 1;
				canvas[l_row][l_col] = reader.next().toCharArray()[0];
				break;
			case "V":
				int v_col = reader.nextInt() - 1;
				int v_row1 = reader.nextInt() - 1;
				int v_row2 = reader.nextInt() - 1;
				char v_color = reader.next().toCharArray()[0];
				if(v_row2 < v_row1) {
					int temp = v_row1;
					v_row1 = v_row2;
					v_row2 = temp;
				}
				for(int row = v_row1; row <= v_row2; row++) {
					canvas[row][v_col] = v_color;
				}
				break;
			case "H":
				int h_col1 = reader.nextInt() - 1;
				int h_col2 = reader.nextInt() - 1;
				int h_row = reader.nextInt() - 1;
				char h_color = reader.next().toCharArray()[0];
				if(h_col2 < h_col1) {
					int temp = h_col1;
					h_col1 = h_col2;
					h_col2 = temp;
				}
				for(int col = h_col1; col <= h_col2; col++) {
					canvas[h_row][col] = h_color;
				}
				break;
			case "K":
				int k_col1 = reader.nextInt() - 1;
				int k_row1 = reader.nextInt() - 1;
				int k_col2 = reader.nextInt() - 1;
				int k_row2 = reader.nextInt() - 1;
				char fillcolor = reader.next().toCharArray()[0];
				if(k_col2 < k_col1) {
					int temp = k_col1;
					k_col1 = k_col2;
					k_col2 = temp;
				}
				if(k_row2 < k_row1) {
					int temp = k_row1;
					k_row1 = k_row2;
					k_row2 = temp;
				}
				for(int row = k_row1; row <= k_row2; row++) {
					for(int col = k_col1; col <= k_col2; col++) {
						canvas[row][col] = fillcolor;
					}
				}
				break;
			case "S":
				System.out.println(reader.next());
				for(char[] row : canvas) {
					for(char c : row) {
						System.out.print(c);
					}
					System.out.println();
				}
				break;
			case "F":
				int f_col = reader.nextInt() - 1;
				int f_row = reader.nextInt() - 1;
				fill(canvas, f_row, f_col, canvas[f_row][f_col], reader.next().toCharArray()[0]);
				break;
			case "X":
				break out;
			}
		}
		reader.close();
	}
}
