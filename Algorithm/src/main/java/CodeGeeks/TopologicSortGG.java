package CodeGeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopologicSortGG {
	static int[] topoSort(ArrayList<ArrayList<Integer>> list, int N) {
		boolean visited[] = new boolean[10001];
		Arrays.fill(visited, false);
		Stack<Integer> st = new Stack<Integer>();
		for (int i = 0; i < N; i++) // Traverse through all the nodes
		{
			if (!visited[i]) // If the current node is not visited
				topo(list, i, visited, st); // Call the topo function with the current node
		}
		int A[] = new int[st.size()];
		int i = -1;
		while (!st.isEmpty()) // while stack is not empty
		{
			A[++i] = st.peek(); // Push the top of the stack to the array
			st.pop();
		}
		return A;
	}

	static void topo(ArrayList<ArrayList<Integer>> list, int it, boolean visited[], Stack<Integer> s) {
		visited[it] = true; // Mark the current visited node as true
		for (int i = 0; i < list.get(it).size(); i++) // Traverse for all the adjacent nodes
		{
			if (!visited[i]) // If the adjacent node is not visited
				topo(list, list.get(it).get(i), visited, s); // call the topo for the adjacent node
		}
		s.push(it); // Push the node to the stack
	}
}
