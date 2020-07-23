package CodeGeeks;

import java.util.ArrayList;

public class UndirectDetectedCycle {

	public UndirectDetectedCycle() {

	}

	private boolean isCyclicUtil(int i, Boolean[] visited, int parent, ArrayList<ArrayList<Integer>> list) {

		visited[i] = true;

		ArrayList<Integer> children = list.get(i);

		for (Integer c : children)
			if (!visited[c]) {
				if (isCyclicUtil(c, visited, i, list))
					return true;
			} else if (c != parent)
				return true;

		return false;
	}

	public boolean isCyclic(ArrayList<ArrayList<Integer>> list, int V) {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		Boolean[] visited = new Boolean[V];

		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < V; i++) {
			if (!(visited[i]) && isCyclicUtil(i, visited, -1, list)) {
				return true;
			}
		}
		return false;
	}

}
