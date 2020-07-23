package Week5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

public class BalancedSearchTrees {
	public static void main(String[] args) {
		// In in = new In("");
		String[] words = { "tuba", "demir", "muaz", "muavvez", "fatih", "rukiye", "mehmet", "g�zel", "ahmettahir" };// in.readAllStrings();

		ST<String, Queue<Integer>> windices = new ST<String, Queue<Integer>>();

		for (int i = 0; i < words.length; i++) {
			if (!windices.contains(words[i])) {
				Queue<Integer> tmp = new Queue<Integer>();
				tmp.enqueue(i);
				windices.put(words[i], tmp);
			} else {
				Queue<Integer> tmp = windices.get(words[i]);
				tmp.enqueue(i);
				windices.put(words[i], tmp);
			}
		}

		int bestlo = -1;
		int besthi = words.length;
		String[] query = { "merhaba", "demir" };
		; // StdIn.readAllStrings();
		Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[query.length];

		for (int i = 0; i < query.length; i++) {
			queues[i] = windices.get(query[i]);
		}

		Queue<Integer> starts = windices.get(query[0]);

		for (Integer start : starts) {
			boolean end = true;
			int lo = start;
			int hi = lo;

			for (int i = 1; i < queues.length; i++) {
				while (!queues[i].isEmpty() && queues[i].peek() <= hi)
					queues[i].dequeue();
				if (queues[i].isEmpty()) {
					end = false;
					break;
				} else {
					hi = queues[i].peek();
				}
			}
			if (end && hi - lo < besthi - bestlo) {
				besthi = hi;
				bestlo = lo;
			}
		}

		if (bestlo >= 0) {
			int interval = besthi - bestlo;
			System.out.println("Shortest interval found: " + interval);
		} else {
			System.out.println("Not found");
		}
	}

	/*
	 * Question 3 Generalized queue. Design a generalized queue data type that
	 * supports all of the following operations in logarithmic time (or better) in
	 * the worst case. Create an empty data structure. Append an item to the end of
	 * the queue. Remove an item from the front of the queue. Return the ith item in
	 * the queue. Remove the ith item from the queue.
	 */

	class GeneralizedQueue<Item> {
		private int index;
		private RedBlackBST<Integer, Item> store;

		GeneralizedQueue() {
			index = 0;
			store = new RedBlackBST<Integer, Item>();
		}

		public void append(Item item) {
			store.put(index++, item);
		}

		public void removeFront() {
			store.deleteMin();
		}

		public Item get(int i) {
			int key = store.rank(i);
			return store.get(key);
		}

		public void delete(int i) {
			store.delete(store.rank(i));
		}
	}
}
