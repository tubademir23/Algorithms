package CodeGeeks;

import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class GFG {
	static int V = 9;

	public GFG(int n) {
		this.V = n;
	}

	int minDistance(int dist[], Boolean sptSet[]) {
		// Initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	// A utility function to print the constructed distance array
	void printSolution(int dist[]) {
		System.out.println("Vertex \t\t Distance from Source");
		for (int i = 0; i < V; i++)
			System.out.println(i + " \t\t " + dist[i]);
	}

	public static int tryParse(String value, int defaultVal) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	void dijkstra(int graph[][], int src) {
		int dist[] = new int[V]; // The output array. dist[i] will hold
		// the shortest distance from src to i

		// sptSet[i] will true if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		Boolean sptSet[] = new Boolean[V];

		// Initialize all distances as INFINITE and stpSet[] as false
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		// Distance of source vertex from itself is always 0
		dist[src] = 0;

		// Find shortest path for all vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices
			// not yet processed. u is always equal to src in first
			// iteration.
			int u = minDistance(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet[u] = true;

			// Update dist value of the adjacent vertices of the
			// picked vertex.
			for (int v = 0; v < V; v++)

				// Update dist[v] only if is not in sptSet, there is an
				// edge from u to v, and total weight of path from src to
				// v through u is smaller than current value of dist[v]
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}

		// print the constructed distance array
		printSolution(dist);
	}

	private static int[][] setGraph(int n) {
		int[][] graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					graph[i][j] = 0;
				} else if (i % 2 == 0 && j % 2 == 0) {
					graph[i][j] = i * 3;
				} else {
					graph[i][j] = i + 1;
				}
			}
		}
		return graph;
	}

	static int getOutput(int n) {
		int cnt = 0;

		while (n > 1) {

			if (n % 3 == 0)
				n = n / 3;
			else
				--n;
			cnt++;
			StdOut.println(n);
		}
		if (n % 3 == 0)
			cnt++;
		return cnt;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		System.out.println(str);
		int testCount = tryParse(str, 0), n = 0;

		if (testCount < 1 || testCount > 30)
			throw new IllegalArgumentException();
		for (int i = 0; i < testCount; i++) {
			String strItem = StdIn.readLine();
			n = tryParse(strItem, 0);
			if (n < 1 || n > 1000)
				throw new IllegalArgumentException();

			StdOut.println(getOutput(n));
			/*
			 * int graph[][] = setGraph(n); GFG t = new GFG(n); t.dijkstra(graph, 0);
			 */

		}
	}
}