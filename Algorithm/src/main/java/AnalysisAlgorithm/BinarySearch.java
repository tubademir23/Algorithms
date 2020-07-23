package AnalysisAlgorithm;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {
	public static int binarySearch(int[] a, int key) {
		int lo = 0, hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static void main(String[] args) {

		int[] a = { 1, 10, 16, 20, 23, 25, 28, 30, 35, 37, 39, 40, 46, 48, 50, 56, 59, 66, 70, 76, 78, 80, 85, 88, 96,
				100 };
		int key = 50;
		int index = binarySearch(a, key);
		StdOut.println(index);

	}
}
