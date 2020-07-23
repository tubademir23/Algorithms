package Week4;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

public class DynamicMedian {
	private MaxPQ<Integer> left;
	private MinPQ<Integer> right;
	
	DynamicMedian() {
		left = new MaxPQ<Integer>();
		right = new MinPQ<Integer>();
	}

	public double findMedian() {
		int l = left.size();
		int r = right.size();
		
		if (l == r)
			return ((double) left.max() + (double) right.min()) / 2;
		else if (l > r)
			return left.max();
		else
			return right.min();
	}

	public void insert(int key) {
		double median = findMedian();
		int l = left.size();
		int r = right.size();
		if (key <= median) {
			left.insert(key);
			if (l> r+1)
				right.insert(left.delMax());
		} else {
			right.insert(key);
			if (r > l+1)
				left.insert(right.delMin());
		}
	}

	public void removeMedian() {
		int l = left.size();
		int r = right.size();
		if (l > r) {
			left.delMax();
		} else {
			right.delMin();
		}
	}
}
