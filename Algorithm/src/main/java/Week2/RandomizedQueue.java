package Week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] a;
	private int n;

	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		n = 0;
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// return the number of items on the randomized queue
	public int size() {
		return n;
	}

	private Item valueAtIndex(int ndx) {
		if (a == null || ndx < 0 || ndx >= a.length || a.length == 0)
			throw new IllegalArgumentException();
		return a[ndx];
	}

	private void resize(int length) {
		assert length >= n;
		a = java.util.Arrays.copyOf(a, length);

	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (a.length == n) {
			resize(a.length * 2); // resize and multiple length with 2
		}
		a[n++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		int ndx = sampleNdx();
		Item item = a[ndx];
		a[ndx] = a[--n];
		a[n] = null;
		if (n > 0 && n == a.length / 4)
			resize(a.length / 2);
		return item;
	}

	private int sampleNdx() {
		if (n == 0)
			throw new NoSuchElementException();
		return StdRandom.uniform(n);
	}

	// return a random item (but do not remove it)
	public Item sample() {
		int ndx = sampleNdx();
		Item item = a[ndx];
		return item;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		int current = 0;

		public boolean hasNext() {
			return current != n;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return a[current++];
		}

	}

	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			rq.enqueue(StdRandom.uniform(100));
			StdOut.println("Enqueu operation: " + rq.valueAtIndex(i));
		}
		assert (rq.a.length == 10);
		for (int i = 0; i < 10; i++) {
			StdOut.println("Dequeu operation: " + rq.dequeue());
		}
		assert (rq.a.length == (10 - 5));
	}

}
