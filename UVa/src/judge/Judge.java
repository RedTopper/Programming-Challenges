package judge;

import java.util.Scanner;

public class Judge {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int run = 0;
		while(reader.hasNext()) {
			run++;
			int acceptedLines = reader.nextInt();
			if(acceptedLines < 1) break;
			reader.nextLine(); //Advance reader beyond int
			String[] accepted = new String[acceptedLines];
			String slightlyAccepted = "";
			for(int i = 0; i < acceptedLines; i++) {
				accepted[i] = reader.nextLine();
				slightlyAccepted += accepted[i];
			}
			int userLines = reader.nextInt();
			reader.nextLine();
			String[] user = new String[userLines];
			String slightlyUser = "";
			for(int i = 0; i < userLines; i++) {
				user[i] = reader.nextLine();
				slightlyUser += user[i];
			}
			if(acceptedLines == userLines) {
				boolean correct = true;
				for(int i = 0; i < acceptedLines; i++) {
					if(!accepted[i].equals(user[i])) {
						correct = false;
						break;
					}
				}
				if(correct) {
					System.out.println("Run #" + run + ": Accepted");
					continue;
				}
			} 
			if(slightlyAccepted.replaceAll("[\\D]", "").equals(slightlyUser.replaceAll("[\\D]", ""))) {
				System.out.println("Run #" + run + ": Presentation Error");
			} else {
				System.out.println("Run #" + run + ": Wrong Answer");
			}
		}
		reader.close();	
	}
}
