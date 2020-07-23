package Week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int n; // number of elements on queue

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
	}

	public Deque() {
		first = null;
		last = null;
		n = 0;
		assert check();
	}

	// is the deque empty?
	public boolean isEmpty() {
		return n == 0;
	}

	// return the number of items on the deque
	public int size() {
		return n;
	}

	private Node<Item> controlAddedItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}

		Node<Item> addedNode = new Node<Item>();
		addedNode.item = item;
		n++;
		return addedNode;
	}

	// add the item to the front
	public void addFirst(Item item) {
		Node<Item> addedNode = controlAddedItem(item);

		if (first == null) {
			last = addedNode;
			first = addedNode;
		} else {
			Node<Item> tempFirst = first;
			first = addedNode;
			first.next = tempFirst;
			first.prev = null;
			tempFirst.prev = first;
		}
		assert check();
	}

	// add the item to the back
	public void addLast(Item item) {
		Node<Item> addedNode = controlAddedItem(item);
		if (last == null) {
			last = addedNode;
			first = addedNode;
		} else {
			Node<Item> tempLast = last;
			last = addedNode;
			last.next = null;
			last.prev = tempLast;
			tempLast.next = last;
		}
		assert check();
	}

	private void controlRemovedItem() {
		if (n == 0)
			throw new NoSuchElementException();

	}

	// remove and return the item from the front
	public Item removeFirst() {
		controlRemovedItem();
		Item item = first.item;

		if (n == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		n--;
		assert check();
		return item;
	}

	// remove and return the item from the back
	public Item removeLast() {
		controlRemovedItem();
		Item item = last.item;

		if (n == 1) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		n--;
		assert check();
		return item;
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node<Item> current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	private boolean check() {

		// check a few properties of instance variable 'first'
		if (n < 0) {
			return false;
		}
		switch (n) {
		case 0:
			if (first != null)
				return false;
			break;
		case 1:
			if (first == null)
				return false;
			if (last == null)
				return false;
			if (first.next != null)
				return false;
			if (first.prev != null)
				return false;
			break;
		default:
			if (first == null)
				return false;
			if (first.next == null)
				return false;
			if (first.prev == null)
				return false;
			break;
		}

		// check internal consistency of instance variable n
		int numberOfNodes = 0;
		for (Node<Item> x = first; x != null && numberOfNodes <= n; x = x.next) {
			numberOfNodes++;
		}
		return numberOfNodes == n;
	}

	public static void main(String[] args) {
		Deque<Integer> dq = new Deque<Integer>();
		dq.addFirst(6);
		dq.addLast(2);
		assert (dq.first.next.item == dq.last.item);

		dq.removeFirst();
		dq.removeFirst();
		assert (dq.first == null);
		assert (dq.last == null);

		dq.addLast(9);
		dq.addLast(2);
		dq.addLast(5);
		assert (dq.last.item == 5);
		assert (dq.first.item == 9);
		assert (dq.n == 3);

	}
}
