import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private final int top;
	private final int bottom;
	private final int size;
	private final WeightedQuickUnionUF qf;
	private int numberOfOpenSites;

	/**
	 * creates n-by-n grid, with all sites initially blocked
	 * 
	 * @param n
	 */
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		size = n;
		top = 0;
		numberOfOpenSites = 0;
		bottom = size * size + 1;
		qf = new WeightedQuickUnionUF(size * size + 2);
		grid = new boolean[size][size];
	}

	/**
	 * if (row, col) is out of bounds? return true else false
	 * 
	 * @param row
	 * @param col
	 */
	private void isArrayIndexOutOfBounds(int row, int col) {
		if (row <= 0 || row > size || col <= 0 || col > size)
			throw new IllegalArgumentException("row and col must be between 0 and " + size);
	}

	/**
	 * opens the site (row, col) if it is not open already
	 * 
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {
		isArrayIndexOutOfBounds(row, col);

		if (isOpen(row, col)) {
			return;
		}
		grid[row - 1][col - 1] = true;
		numberOfOpenSites++;
		if (row == 1) { // top
			qf.union(getSiteIndex(row, col), top);
		}
		if (row == size) { // bottom
			qf.union(getSiteIndex(row, col), bottom);
		}

		// combination neightbours
		if (col > 1 && isOpen(row, col - 1)) {
			qf.union(getSiteIndex(row, col), getSiteIndex(row, col - 1));
		}
		if (col < size && isOpen(row, col + 1)) {
			qf.union(getSiteIndex(row, col), getSiteIndex(row, col + 1));
		}
		if (row > 1 && isOpen(row - 1, col)) {
			qf.union(getSiteIndex(row, col), getSiteIndex(row - 1, col));
		}
		if (row < size && isOpen(row + 1, col)) {
			qf.union(getSiteIndex(row, col), getSiteIndex(row + 1, col));
		}

	}

	/**
	 * is the site (row, col) open?
	 * 
	 * @param row
	 * @param col
	 * @return true if site (row i, column j) is full; false otherwise
	 */
	public boolean isOpen(int row, int col) {
		isArrayIndexOutOfBounds(row, col);
		return grid[row - 1][col - 1];
	}

	/**
	 * is the site (row, col) full?
	 * 
	 * @param row
	 * @param col
	 * @return true if site (row i, column j) is full; false otherwise
	 */

	public boolean isFull(int row, int col) {
		isArrayIndexOutOfBounds(row, col);
		return qf.connected(getSiteIndex(row, col), top);
	}

	/**
	 * does the system percolate?
	 * 
	 * @return
	 */
	public boolean percolates() {
		return numberOfOpenSites > 0 && qf.connected(top, bottom);
	}

	/**
	 * is the site (row, col) full?
	 * 
	 * @param row
	 * @param col
	 * @return index of row, col on the site
	 */

	private int getSiteIndex(int row, int col) {
		return size * (row - 1) + col;
	}

	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
}
