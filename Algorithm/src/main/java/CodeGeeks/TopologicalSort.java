package CodeGeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Queue;

public class TopologicalSort {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			ArrayList<ArrayList<Integer>> list = new ArrayList();
			String st[] = br.readLine().trim().split("\\s+");
			int nov = Integer.parseInt(st[0]);
			int edg = Integer.parseInt(st[1]);
			for (int i = 0; i < nov + 1; i++) {
				list.add(i, new ArrayList<Integer>());
			}
			String str = br.readLine();
			String s[] = str.trim().split("\\s+");

			// System.out.println(
			// "nov: " + nov + " edg: " + edg + " s:" + list.size() + " str: " + str + "
			// s.size" + s.length);
			int p = 0;
			for (int i = 0; i < edg; i++) {

				int u = Integer.parseInt(s[p++]);
				int v = Integer.parseInt(s[p++]);

				list.get(u).add(v);
			}
			int res[] = new int[10001];
			res = new TopologicSortGG().topoSort(list, nov);
			boolean valid = false;
			for (int i = 0; i < nov; i++) {
				int n = list.get(res[i]).size();
				for (int j = 0; j < list.get(res[i]).size(); j++) {
					for (int k = i + 1; k < nov; k++) {
						if (res[k] == list.get(res[i]).get(j))
							n--;
					}
				}
				if (n != 0) {
					valid = false;
					break;
				}
			}
			System.out.println(valid ? "1" : "0");
		}
	}

	static int[] topoSort(ArrayList<ArrayList<Integer>> list, int N) {
		Queue<Integer> queue = new Queue();
		int in_degree[] = new int[N];
		Boolean visited[] = new Boolean[N];
		int T[] = new int[N + 1];
		for (int i = 0; i < N; i++) {
			in_degree[i] = 0;
			visited[i] = false;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (list.get(i).size() > j && list.get(i).get(j) != null) {
					in_degree[j]++;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			if (in_degree[i] == 0) {
				queue.enqueue(i);
				// visited[i] = true;
			}
		}
		int cnt = 0;
		while (!queue.isEmpty()) {
			int item = queue.peek();
			queue.dequeue();
			T[cnt++] = item;
			for (int i = 0; i < N; i++) {
				if (list.get(item).size() > i && list.get(item).get(i) != null & !visited[i]) {
					in_degree[i]--;
					if (in_degree[i] == 0) {
						queue.enqueue(i);
						visited[i] = true;
					}
				}
			}

		}
		return T;
	}
	/*
	 * topological_sort(N, adj[N][N]) T = [] visited = [] in_degree = [] for i = 0
	 * to N in_degree[i] = visited[i] = 0
	 * 
	 * for i = 0 to N for j = 0 to N if adj[i][j] is TRUE in_degree[j] =
	 * in_degree[j] + 1
	 * 
	 * for i = 0 to N if in_degree[i] is 0 enqueue(Queue, i) visited[i] = TRUE
	 * 
	 * while Queue is not Empty vertex = get_front(Queue) dequeue(Queue)
	 * T.append(vertex) for j = 0 to N if adj[vertex][j] is TRUE and visited[j] is
	 * FALSE in_degree[j] = in_degree[j] - 1 if in_degree[j] is 0 enqueue(Queue, j)
	 * visited[j] = TRUE return T
	 */

}
