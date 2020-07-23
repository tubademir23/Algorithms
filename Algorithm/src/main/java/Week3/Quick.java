package Week3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	// and return the index j.
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		while (true) {
			// find item on left to swap
			while (less(a[++i], a[lo]))
				if (i == hi)
					break;
			// find item on right to swap
			while (less(a[lo], a[--j]))
				if (j == lo)
					break;
			// check if pointers cross swap
			if (i >= j)
				break;

		}
		// swap with partition item
		exch(a, lo, j);
		// return index of item now known to be in place
		return j;
	}

	private static boolean less(Comparable v, Comparable w) {
		if (v == w)
			return false; // optimization when reference equals
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static void show(Comparable[] a) {
		for (Comparable c : a) {
			StdOut.print(c + " ");
		}
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	public static void main(String[] args) {
		String[] a = args[0].split(",");
		sort(a);

	}
}
