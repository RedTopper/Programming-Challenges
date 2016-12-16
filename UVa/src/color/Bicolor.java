package color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bicolor {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			int nodes = reader.nextInt();
			if(nodes == 0) break;
			int edges = reader.nextInt();
			List<List<Integer>> graph = new ArrayList<>();
			for(int i = 0; i < nodes; i++) graph.add(new ArrayList<Integer>());
			int[] colors = new int[nodes];
			for(int i = 0; i < edges; i++) {
				int from = reader.nextInt();
				int to = reader.nextInt();
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
			colors[0] = 1;
			System.out.println(traverse(graph, colors, 0) ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
		reader.close();
	}

	private static boolean traverse(List<List<Integer>> graph, int[] colors, int colorIndex) {
		boolean done = true;
		for(int i = 0; i < graph.get(colorIndex).size(); i++) {
			int index = graph.get(colorIndex).get(i);
			int color = colors[index];
			if(color != 0 && color == colors[colorIndex]) {
				return false;
			}
			if(color == 0) {
				colors[index] = (colors[colorIndex] == 1 ? 2 : 1);
				done = done && traverse(graph, colors, index);
			}
		}
		return done;
	}
}
