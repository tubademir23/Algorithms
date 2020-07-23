package Week5;

import edu.princeton.cs.algs4.RedBlackBST;

public class GeneralizedQueue<Item> {
	private int index;
	private RedBlackBST<Integer, Item> queue;

	GeneralizedQueue() {
		index = 0;
		queue = new RedBlackBST<Integer, Item>();
	}

	public void append(Item item) {
		queue.put(index++, item);
	}

	public void removeFront() {
		queue.deleteMin();
	}

	public Item get(int i) {
		int key = queue.rank(i);
		return queue.get(key);
	}

	public void delete(int i) {
		queue.delete(queue.rank(i));
	}
}
