package Stack;

public class LinkedQueueOfStrings<Item> {
	private Node first, last;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(String item) {
		Node oldlast = last;
		last = new Node();
		last.item = (Item) item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
	}

	public String dequeue() {
		String item = (String) first.item;
		first = first.next;
		if (isEmpty())
			last = null;
		return item;
	}
}