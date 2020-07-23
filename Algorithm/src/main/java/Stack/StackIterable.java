package Stack;

import java.util.Iterator;

public class StackIterable<Item> extends Stack<Item> implements Iterable<Item> {

	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}

	private class ListIterator<Item> implements Iterator<Item> {

		private Node current = getFirst();

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		public Item next() {
			// TODO Auto-generated method stub
			Item item = (Item) current.item;
			current = current.next;
			return item;
		}

	}

}
