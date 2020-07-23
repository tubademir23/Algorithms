package Week4;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private SearchNode goal; // link to the goal board
	private boolean solvableTwin; // does the initial board have a solvable twin?
	private MinPQ<SearchNode> pq; // priority queue for quickly finding the mininum value
	private MinPQ<SearchNode> twinpq; // priority queue for the twin board

	// inner class SearchNode for storing a board and a link to its parent
	private class SearchNode {
		private Board board;
		private SearchNode parent;
		private int moves;

		private SearchNode(Board b, SearchNode node, int m) {
			board = b;
			parent = node;
			moves = m;
		}

		public int getManhattan() {
			return board.manhattan() + moves;
		}
	}

	// comparator for the priority queue. sort based on manhattan score.
	private final Comparator<SearchNode> manhattanPriority = new Comparator<SearchNode>() {
		public int compare(SearchNode node1, SearchNode node2) {
			int distance1 = node1.getManhattan();
			int distance2 = node2.getManhattan();
			return Integer.compare(distance1, distance2);
		}
	};

	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * 
	 * @param Board the initial board to solve
	 */
	public Solver(Board initial) {

		this.solvableTwin = false;
		this.goal = null;
		this.pq = new MinPQ<SearchNode>(manhattanPriority);
		this.twinpq = new MinPQ<SearchNode>(manhattanPriority);

		// initialize the current SearchNode
		SearchNode current = null;
		SearchNode currentTwin = null;

		pq.insert(new SearchNode(initial, null, 0));
		twinpq.insert(new SearchNode(initial.twin(), null, 0));

		while (!pq.isEmpty()) {
			current = pq.delMin(); // pop off the lowest priority SearchNode
			currentTwin = twinpq.delMin();

			// current or curentTwin is goal, then break the loop
			if (current.board.isGoal()) {
				goal = current;
				break;
			} else if (currentTwin.board.isGoal()) {
				solvableTwin = true;
				break;
			}
			// else we need move then loop the neighbours
			else {
				for (Board b : current.board.neighbors()) {
					if (current.parent == null) {
						SearchNode node = new SearchNode(b, current, current.moves + 1);
						pq.insert(node);
					} else if (!b.equals(current.parent.board)) {
						SearchNode node = new SearchNode(b, current, current.moves + 1);
						pq.insert(node);
					}
				}
				for (Board b : currentTwin.board.neighbors()) {
					if (currentTwin.parent == null) {
						SearchNode node = new SearchNode(b, current, currentTwin.moves + 1);
						twinpq.insert(node);
					} else if (!b.equals(currentTwin.parent.board)) {
						SearchNode node = new SearchNode(b, currentTwin, currentTwin.moves + 1);
						twinpq.insert(node);
					}
				}
			}
		}
	}

	/**
	 * is the initial board solvable?
	 * 
	 * @return true if its solvable, false if not
	 */
	public boolean isSolvable() {
		return !solvableTwin;
	}

	/**
	 * min number of moves to solve initial board; -1 if no solution
	 * 
	 * @return number of moves made to reach the solution
	 */
	public int moves() {
		// make sure the puzzle is actually solvable
		if (isSolvable()) {
			return goal.moves;
		} else {
			return -1;
		}
	}

	/**
	 * sequence of boards in a shortest solution
	 * 
	 */
	public Iterable<Board> solution() {
		// make sure the puzzle is solvable
		if (!isSolvable()) {
			return null;
		}

		// we're traversing backwards so we want LIFO functionality and
		// a Stack gives us just that
		Stack<Board> solution = new Stack<Board>();

		// follow the chain of parents until the end
		SearchNode current = goal;
		while (current.parent != null) {
			solution.push(current.board);
			current = current.parent;
		}

		// need to be sure to push the last board onto the Stack
		solution.push(current.board);

		return solution;
	}

	/**
	 * solve a slider puzzle
	 */
	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				blocks[i][j] = in.readInt();
			}
		}
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
}