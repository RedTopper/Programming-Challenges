package soccer;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Soccer {
	
	static class Team {
		String teamName;
		int rank;
		int points;
		int wins;
		int ties;
		int loss;
		int played;
		int goalScored;
		int goalAgainst;
		
		private Team(String teamName) {
			this.teamName = teamName;
		}
		
		public boolean equals(Object other) {
			if (!(other instanceof Team))return false;
			return teamName.equals(((Team)other).teamName);
		}
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in,  "ISO-8859-1");
		PrintWriter out = null;
		try {out = new PrintWriter(new OutputStreamWriter(System.out,  "ISO-8859-1"));} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		int tournies = reader.nextInt(); reader.nextLine();
		while(tournies-- > 0) {
			ArrayList<Team> teams = new ArrayList<>();
			out.println(reader.nextLine());
			int count = reader.nextInt(); reader.nextLine();
			while(count-- > 0) {
				teams.add(new Team(reader.nextLine()));
			}
			int games = reader.nextInt(); reader.nextLine();
			while(games-- > 0) {
				String line = reader.nextLine();
				String[] game = line.split("[#@]");
				Team t1 = teams.get(teams.indexOf(new Team(game[0])));
				Team t2 = teams.get(teams.indexOf(new Team(game[3])));
				int t1goals = Integer.parseInt(game[1]);
				int t2goals = Integer.parseInt(game[2]);
				if(t1goals > t2goals) {
					t1.wins++;
					t1.points += 3;
					t2.loss++;
				} else if(t1goals < t2goals) {
					t2.wins++;
					t2.points += 3;
					t1.loss++;
				} else {
					t1.ties++;
					t1.points++;
					t2.ties++;
					t2.points++;
				}
				t1.played++;
				t1.goalScored += t1goals;
				t1.goalAgainst += t2goals;
				t2.played++;
				t2.goalScored += t2goals;
				t2.goalAgainst += t1goals;
			}
			teams.sort(new Comparator<Team>() {
				public int compare(Team o1, Team o2) {
					int points =  o2.points - o1.points;
					if(points != 0) {
						return points;
					}
					int wins = o2.wins - o1.wins;
					if(wins != 0) {
						return wins;
					}
					int goalDifference = (o2.goalScored - o2.goalAgainst) - (o1.goalScored - o1.goalAgainst);
					if(goalDifference != 0) {
						return goalDifference;
					}
					int goalScored = o2.goalScored - o1.goalScored;
					if(goalScored != 0) {
						return goalScored;
					}
					int games = o1.played - o2.played;
					if(games != 0) {
						return games;
					}
					return o1.teamName.toLowerCase().compareTo(o2.teamName.toLowerCase());
				}
			});
			for(int i = 0; i < teams.size(); i++) {
				Team t = teams.get(i);
				out.println((i + 1) + ") " + t.teamName + " " + t.points + "p, " + t.played + "g (" + t.wins + "-" + t.ties + "-" + t.loss + "), " + (t.goalScored - t.goalAgainst) + "gd (" + t.goalScored + "-" + t.goalAgainst + ")");
			}
			if(tournies != 0) out.println();
		}
		reader.close();
		out.flush();
	}
}
