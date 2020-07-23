package Week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.princeton.cs.algs4.StdOut;

public class NutsAndBolts<T extends Comparable<T>> {
	private final T[] nuts;
	private final T[] bolts;

	public NutsAndBolts(T[] nuts, T[] bolts) {
		if (nuts == null || bolts == null)
			throw new IllegalArgumentException("Nuts or bolts can not be null");
		if (bolts.length != nuts.length)
			throw new IllegalArgumentException("Bolts and nuts length must be equal for pairing");

		this.nuts = nuts;
		this.bolts = bolts;
	}

	private void show() {
		for (int i = 0; i < nuts.length; i++) {
			StdOut.println((i < bolts.length ? bolts[i] : "_") + " -> " + (i < nuts.length ? nuts[i] : "_"));
		}

	}

	private void matchNAB(int lo, int hi) {
		if (lo < hi) {
			// StdOut.println("lo: " + lo + " hi: " + hi);
			// show(nuts, bolts);
			int pivot = partition(nuts, lo, hi, bolts[hi]);

			partition(bolts, lo, hi, nuts[pivot]);

			matchNAB(lo, pivot - 1);
			matchNAB(pivot + 1, hi);
		}
	}

	private int partition(T[] a, int lo, int hi, T pivot) {
		int i = lo, j = lo;

		while (j < hi) {
			if (a[j].compareTo(pivot) < 0) {
				exch(a, i++, j);
			} else if (a[j] == pivot) {
				exch(a, j--, hi);
			}
			j++;
		}

		exch(a, i, hi);
		return i;
	}

	private void exch(T[] a, int i, int j) {
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void intExample() {
		Integer nuts[] = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] bolts = new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

		NutsAndBolts nb = new NutsAndBolts(nuts, bolts);
		nb.matchNAB(0, nuts.length - 1);
		nb.show();
	}

	public static void charExample() {
		Character nuts[] = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
		Character bolts[] = new Character[] { 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a' };

		NutsAndBolts nb = new NutsAndBolts(nuts, bolts);
		nb.matchNAB(0, nuts.length - 1);
		nb.show();
	}

	public static <T> T kNdx(T[] a, T[] b, int k) {
		if (a == null || b == null)
			throw new IllegalArgumentException("Nuts or bolts can not be null");
		int n1 = a.length, n2 = b.length;
		if (k < 0 || k >= n1 + n2)
			throw new IllegalArgumentException("Nuts or bolts can not be null");
		// merge a and b to <T> array
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < n1; i++) {
			list.add(a[i]);
		}
		for (int i = 0; i < n2; i++) {
			list.add(b[i]);
		}
		T[] ab = (T[]) list.toArray();

		int i = 0, j = 0, d = 0;
		while (i < n1 && j < n2) {
			if (((Comparable<T>) a[i]).compareTo(b[j]) < 0)
				ab[d++] = a[i++];
			else
				ab[d++] = b[j++];
		}
		while (i < n1)
			ab[d++] = a[i++];
		while (j < n2)
			ab[d++] = b[j++];
		return ab[k - 1];
	}

	public Vector<Integer> majorityElement(Vector<Integer> nums) {
		int cnt1 = 0, cnt2 = 0;
		int a = 0, b = 0;
		for (int n : nums) {
			if (cnt1 == 0 || n == a) {
				cnt1++;
				a = n;
			} else if (cnt2 == 0 || n == b) {
				cnt2++;
				b = n;
			} else { // This part
				cnt1--;
				cnt2--;
			}
		}
		cnt1 = cnt2 = 0;
		for (int n : nums) {
			if (n == a)
				cnt1++;
			else if (n == b)
				cnt2++;
		}
		Vector<Integer> result = new Vector<Integer>();
		if (cnt1 > nums.size() / 3)
			result.add(a);
		if (cnt2 > nums.size() / 3)
			result.add(b);
		return result;
	}

	public static void main(String[] args) {
		intExample();
		charExample();
		Integer arr1[] = { 2, 3, 6, 7, 9 };
		Integer arr2[] = { 1, 4, 8, 10 };
		int k = 5;
		StdOut.println(kNdx(arr1, arr2, k));

	}

}