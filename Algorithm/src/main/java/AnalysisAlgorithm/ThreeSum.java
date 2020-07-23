package AnalysisAlgorithm;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//import edu.princeton.cs.algs4.ThreeSum;
public class ThreeSum {
	public static int count(int[] a) {
		int N = a.length;
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					if (a[i] + a[j] + a[k] == 0)
						count++;
		return count;
	}

	public static void main(String[] args) {
		final String path = "D://algs4-data//";
		long startTime, endTime, elapsedTime;
		String filenames[] = { "8ints.txt", "1Kints.txt", "2Kints.txt", "2Kints.txt", "4Kints.txt", "8Kints.txt" };
		for (String filename : filenames) {
			startTime = System.nanoTime();
			int[] a = In.readInts(path + filename);
			int count = count(a);
			endTime = System.nanoTime();
			elapsedTime = endTime - startTime;
			StdOut.println(filename + " \t" + elapsedTime / 1000000);
			StdOut.println(count);
		}

	}
}
