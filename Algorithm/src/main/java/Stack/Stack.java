package Stack;

import edu.princeton.cs.algs4.StdOut;

public class Stack<Item> {
	private Node first = null;

	private int size = 0;

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first_) {
		first = first_;
	}

	protected class Node {
		Item item;
		Node next;
	}

	public boolean isempty() {
		return first == null;
	}

	public void push(Item item) {
		StdOut.println("push" + item);
		Node curFirst = first;
		first = new Node();
		first.item = item;
		first.next = curFirst;
		size++;
	}

	public Item pop() {

		Item item = first.item;
		first = first.next;
		StdOut.println("pop" + item);
		size--;
		return item;

	}

	public Item peek() {
		Item item = first.item;
		return item;
	}

	public int getSize() {
		return size;
	}
}
