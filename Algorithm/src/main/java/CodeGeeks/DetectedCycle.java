package CodeGeeks;

import java.util.ArrayList;
import java.util.List;

public class DetectedCycle {

	public DetectedCycle() {

	}

	private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, ArrayList<ArrayList<Integer>> list) {

		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;
		List<Integer> children = list.get(i);

		for (Integer c : children)
			if (isCyclicUtil(c, visited, recStack, list))
				return true;

		recStack[i] = false;

		return false;
	}

	public boolean isCyclic(ArrayList<ArrayList<Integer>> list, int V) {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack, list))
				return true;

		return false;
	}

}
