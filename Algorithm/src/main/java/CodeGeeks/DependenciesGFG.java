package CodeGeeks;

import java.util.Scanner;

public class DependenciesGFG {
	int N, E;
	int nodes[];
	int edges[][];
	int count;

	public DependenciesGFG(int n, int e, String lineEdges) {
		E = e;
		N = n;
		nodes = new int[N];
		count = 0;
		edges = new int[E][2];
		int idx = 0;
		for (String is : lineEdges.split(" ")) {
			int nodeNbr = Integer.parseInt(is);
			edges[idx / 2][idx % 2] = nodeNbr;
			idx++;
		}
		for (int i = 0; i < E; i++) {
			nodes[edges[i][0]]++;
		}
		for (int i = 0; i < N; i++) {
			count += nodes[i];
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testCount = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < testCount; i++) {
			String lineNE = scanner.nextLine();
			int n = Integer.parseInt(lineNE.split(" ")[0]);
			int e = Integer.parseInt(lineNE.split(" ")[1]);
			String line = scanner.nextLine();
			DependenciesGFG gfg = new DependenciesGFG(n, e, line);
			System.out.println(gfg.count);
		}
	}
}
