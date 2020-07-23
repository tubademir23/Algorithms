package CodeGeeks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

	/*
	 * ArrayList<ArrayList<Integer>> list: represent graph containing vertices and
	 * edges between them vis[]: boolean array to represent visited vertex s:
	 * starting vertex
	 */
	static void bfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[], int nov) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		LinkedList<Integer> result = new LinkedList<Integer>();
		while (!queue.isEmpty()) {
			int e = queue.remove();
			if (result.indexOf(e) < 0) {
				System.out.print(e + " ");
				result.add(e);
				for (int neighbour : list.get(e)) {
					if (e != neighbour && !vis[neighbour]) {
						queue.add(neighbour);
						vis[neighbour] = true;
					}
				}
			}
		}
	}

	public static void toString(ArrayList<ArrayList<Integer>> list, int nov) {
		for (int i = 0; i < nov; i++) {
			System.out.println(i + ": ");
			for (int neighbour : list.get(i)) {
				System.out.print(neighbour + " ");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList();
			int nov = sc.nextInt();
			int edg = sc.nextInt();
			for (int i = 0; i < nov; i++) {
				list.add(i, new ArrayList<Integer>());
			}
			for (int i = 1; i <= edg; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				list.get(u).add(v);
			}

			boolean[] vis = new boolean[nov];
			for (int i = 0; i < nov; i++) {
				vis[i] = false;
			}
			bfs(0, list, vis, nov);
		}
	}
}
