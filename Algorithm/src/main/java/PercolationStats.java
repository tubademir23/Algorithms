import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int experimentsCount;
	private final double[] fractions;
	private final static double CONFIDENCE_95 = 1.96;

	private static double mean, stddev;

	/**
	 * perform independent trials on an n-by-n grid
	 * 
	 * @param n
	 * @param trials
	 */
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
		}
		mean = stddev = 0;
		experimentsCount = trials;
		fractions = new double[experimentsCount];
		for (int expNum = 0; expNum < experimentsCount; expNum++) {
			Percolation pr = new Percolation(n);
			while (!pr.percolates()) {
				int i = StdRandom.uniform(1, n + 1);
				int j = StdRandom.uniform(1, n + 1);
				if (!pr.isOpen(i, j)) {
					pr.open(i, j);
				}
			}
			double fraction = (double) pr.numberOfOpenSites() / (n * n);
			fractions[expNum] = fraction;
		}
	}

	/**
	 * sample mean of percolation threshold
	 * 
	 * @return
	 */
	public double mean() {
		return StdStats.mean(fractions);
	}

	/**
	 * sample standard deviation of percolation threshold
	 * 
	 * @return
	 */
	public double stddev() {
		return StdStats.stddev(fractions);
	}

	/**
	 * low endpoint of 95% confidence interval
	 * 
	 * @return
	 */
	public double confidenceLo() {
		return mean - ((CONFIDENCE_95 * stddev) / Math.sqrt(experimentsCount));
	}

	/**
	 * high endpoint of 95% confidence interval
	 * 
	 * @return
	 */
	public double confidenceHi() {
		return mean + ((CONFIDENCE_95 * stddev) / Math.sqrt(experimentsCount));
	}

	/**
	 * test client (see below)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int n = -1, t = -1;
		if (args.length != 2)
			throw new IllegalArgumentException();
		else {
			n = Integer.parseInt(args[0]);
			t = Integer.parseInt(args[1]);
		}
		PercolationStats ps = new PercolationStats(n, t);

		mean = ps.mean();
		stddev = ps.stddev();
		StdOut.printf("mean                    = %f\n", mean);
		StdOut.printf("stddev                  = %f\n", stddev);
		StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
	}
}
