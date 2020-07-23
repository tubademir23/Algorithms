package Week4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Taxicab implements Comparable<Taxicab> {
	int n1;
	int n2;
	int exp;

	Taxicab(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
		this.exp = exp(n1, 3) + exp(n2, 3);
	}

	private int exp(int base, int ex_) {
		int result = 1;
		for (int i = 0; i < ex_; i++) {
			result *= base;
		}
		return result;
	}

	public int compareTo(Taxicab that) {

		if (that.exp > this.exp)
			return -1;
		if (that.exp < this.exp)
			return 1;
		return 0;
	}

	public boolean equals(Object o) {
		if (o instanceof Taxicab) {
			// ((Taxicab) o).equals(this);
			if (((Taxicab) o).compareTo(this) == 0)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return exp + ": " + n1 + ", " + n2;
	}

	public void show() {
		StdOut.println(exp + ": " + n1 + ", " + n2);
	}

	public static void findTaxinumber(int n) {
		MinPQ<Taxicab> mq = new MinPQ<Taxicab>();

		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				Taxicab newT = new Taxicab(i, j);
				if (mq.size() < n) {
					mq.insert(newT);
				} else {
					Queue<Taxicab> queue = new Queue<Taxicab>();
					Taxicab min = mq.delMin();
					while (mq.min().equals(min)) {
						queue.enqueue(mq.delMin());
					}
					if (!newT.equals(min)) {
						mq.insert(newT);
					} else {
						queue.enqueue(newT);
					}
					if (!queue.isEmpty()) {
						for (Taxicab taxi : queue) {
							taxi.show();
						}
						min.show();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		findTaxinumber(16);
	}

}
