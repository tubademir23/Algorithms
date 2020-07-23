package CodeGeeks;

import java.util.ArrayList;

public class DFS {
	private ArrayList<ArrayList<Integer>> list;
	private boolean vis[];
	/*
	 * ArrayList<ArrayList<Integer>> list: which represents graph src: represents
	 * source vertex vis[]: boolean array
	 */

	DFS(int vertices) {
		list = new ArrayList<ArrayList<Integer>>(vertices);
		vis = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			list.add(new ArrayList<Integer>());
		}
	}

	void addEdge(int src, int dest) {
		list.get(src).add(dest);
	}

	/*
	 * static void dfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
	 * // add your code here }
	 */
	void dfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
		vis[src] = true;
		System.out.print(src + " ");
		for (int adj : list.get(src)) {
			if (!vis[adj])
				dfs(adj, list, vis);
		}
	}

	public static void main(String args[]) {
		DFS d = new DFS(4);

		d.addEdge(0, 1);
		d.addEdge(0, 2);
		d.addEdge(1, 3);
		d.addEdge(2, 3);

		System.out.println("Following is Depth First Traversal");

		d.dfs(0, d.list, d.vis);
	}
}
