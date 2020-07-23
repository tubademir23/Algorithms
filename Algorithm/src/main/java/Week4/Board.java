package Week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	private int N; // dimension of this board
	private int[] squares; // array containing 8 squares + 1 blank square

	private int blankIndex; // index of the blank square

	private int hammingNumber;
	private int manhattanDistance;

	public Board(int[][] tiles) {
		this.hammingNumber = -1; // initialize for future recalculation
		this.manhattanDistance = -1; // initialize for future recalculation

		this.N = tiles.length;
		this.squares = new int[N * N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int newIndex = (i * N) + j;
				squares[newIndex] = tiles[i][j];

				if (squares[newIndex] == 0) {
					this.blankIndex = newIndex;
				}
			}
		}
		manhattan();
		hamming();
	}

	private Board(int[] blocks) {
		this.hammingNumber = -1;
		this.manhattanDistance = -1;

		this.N = (int) Math.sqrt(blocks.length);
		this.squares = new int[N * N];

		for (int i = 0; i < (N * N); i++) {
			squares[i] = blocks[i];

			if (squares[i] == 0) {
				this.blankIndex = i;
			}
		}
		manhattan();
		hamming();
	}

	public int manhattan() {
		if (manhattanDistance >= 0)
			return manhattanDistance;
		manhattanDistance = 0;
		for (int i = 0; i < (N * N); i++) {
			int value = squares[i];

			if (value != 0) {
				int goalRow = (value - 1) / N;
				int goalCol = (value - 1) % N;
				int row = i / N;
				int col = i % N;
				int distance = Math.abs(goalRow - row) + Math.abs(goalCol - col);
				manhattanDistance += distance;
			}
		}
		return manhattanDistance;
	}

	/**
	 * number of blocks out of place
	 * 
	 */
	public int hamming() {
		if (hammingNumber >= 0)
			return hammingNumber;
		hammingNumber = 0;
		for (int i = 0; i < (N * N); i++) {
			int square = squares[i];

			if (square != 0) {
				int goalValue = i + 1;

				if (goalValue == N * N) {
					goalValue = 0;
				}
				if (goalValue != square) {
					hammingNumber++;
				}
			}
		}

		return hammingNumber;
	}

	/**
	 * helper method swaps two values at the given indices.
	 * 
	 * @param i index of this item
	 * @param j index of that item
	 * @return false if the swap is impossible (because it would swap off the board)
	 *         or true if the swap was successful
	 */
	private boolean swap(int i, int j) {
		// first verify that the swap is even possible
		// test that index for being out of bounds in the array
		if (j < 0 || j >= (N * N)) {
			return false;
		}
		// then make sure that index is either in the same row or same column
		if ((i / N) != (j / N) && (i % N) != (j % N)) {
			return false;
		}

		// snag the values at the given indices
		int iValue = squares[i];
		int jValue = squares[j];

		// if we're swapping the empty tile, swap those instance fields too
		if (iValue == 0) {
			blankIndex = j;
		} else if (jValue == 0) {
			blankIndex = i;
		}

		// finally swap the actual values
		squares[i] = jValue;
		squares[j] = iValue;

		return true;
	}

	/**
	 * board dimension N
	 * 
	 * @return the dimension of the board
	 */
	public int dimension() {
		return N;
	}

	/**
	 * sum of Manhattan distances between blocks and goal
	 * 
	 * @return either the cached manhattan score or the newly recalculated score
	 */

	/**
	 * is this board the goal board?
	 * 
	 * @return true if it is the goal, false if not
	 */
	public boolean isGoal() {
		// loop through each tile and ensure that the value in that tile
		// is the value for the goal board
		for (int i = 0; i < (N * N); i++) {
			int value = squares[i];
			int goalValue = i + 1;

			if (goalValue == (N * N)) {
				goalValue = 0;
			}
			if (goalValue != value) {
				return false;
			}
		}

		return true;
	}

	/**
	 * a board obtained by exchanging two adjacent blocks in the same row, but not
	 * including the empty space
	 * 
	 * @return a valid twin for this board
	 */
	public Board twin() {
		// simply test if the empty space is in the top row, if not
		// swap those first two values.
		// if it is swap the two in the next row
		Board twin = new Board(squares);

		if (twin.squares[0] != 0 && twin.squares[1] != 0) {
			twin.swap(0, 1);
		} else {
			twin.swap(N, N + 1);
		}

		return twin;
	}

	/**
	 * does this board equal y?
	 * 
	 * @param Object the Object (Board) to compare to
	 * @return true if they are the same board, false otherwise
	 */
	public boolean equals(Object y) {
		Board board = (Board) y;
		assert (y == null) || (y.getClass() != this.getClass()) || this.dimension() != board.dimension();

		if (y == this) {
			return true;
		}

		// loop through each tile and see if the values are the same throughout
		for (int i = 0; i < (N * N); i++) {
			if (this.squares[i] != board.squares[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * all neighboring boards
	 */
	public Iterable<Board> neighbors() {
		Queue<Board> neighBoards = new Queue<Board>();

		Board boards[] = new Board[4];
		int[] neigbNdx = { -N, N, -1, 1 };
		for (int i = 0; i < boards.length; i++) {
			boards[i] = new Board(squares);
			if (boards[i].swap(blankIndex, blankIndex + neigbNdx[i]))
				neighBoards.enqueue(boards[i]);
		}

		return neighBoards;
	}

	/**
	 * string representation of the board, n+1 lines show
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(N + "\n");

		for (int i = 0; i < (N * N); i++) {
			sb.append(String.format("%2d ", squares[i]));
			if (((i + 1) % N) == 0) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	/**
	 * unit testing
	 * 
	 * @param String[] command line arguments
	 */
	public static void main(String[] args) {
		// read in the initial board from the given file
		In in = new In(args[0]);
		int N = in.readInt();
		int[][] blocks = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				blocks[i][j] = in.readInt();
			}
		}
		Board board = new Board(blocks);

		StdOut.print(board);
		Board twin = board.twin();
		StdOut.println();
		StdOut.println(twin);

	}
}